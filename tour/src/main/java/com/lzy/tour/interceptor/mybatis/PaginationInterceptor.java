package com.lzy.tour.interceptor.mybatis;

import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang.reflect.FieldUtils;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.log4j.Logger;

import com.lzy.tour.interceptor.mybatis.dialect.Dialect;


/**
 * 分页拦截器, 支持多种数据库
 * 
 */
@Intercepts({@Signature(
		type= Executor.class,
		method = "query",
		args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})})
public class PaginationInterceptor extends AbstractInterceptor {
	private final static Logger logger = Logger.getLogger(PaginationInterceptor.class);
	private static int MAPPED_STATEMENT_INDEX = 0;
	private static int PARAMETER_INDEX = 1;
	private static int ROWBOUNDS_INDEX = 2;

	private Dialect dialect;

	public Object intercept(Invocation invocation) throws Throwable {
		processIntercept(invocation.getArgs());
		return invocation.proceed();
	}

	private void processIntercept(final Object[] queryArgs) {
		MappedStatement ms = (MappedStatement) queryArgs[MAPPED_STATEMENT_INDEX];
		Object parameter = queryArgs[PARAMETER_INDEX];
		final RowBounds rowBounds = (RowBounds) queryArgs[ROWBOUNDS_INDEX];
		int offset = rowBounds.getOffset();
		int limit = rowBounds.getLimit();
		//分页
		if (dialect.supportsLimit() && (offset != RowBounds.NO_ROW_OFFSET || limit != RowBounds.NO_ROW_LIMIT)) {
			BoundSql boundSql = ms.getBoundSql(parameter);
			String sql = boundSql.getSql().trim();
			if (dialect.supportsLimitOffset()) {
				sql = dialect.getLimitString(sql, offset, limit);
				offset = RowBounds.NO_ROW_OFFSET;
			} else {
				sql = dialect.getLimitString(sql, 0, limit);
			}
			limit = RowBounds.NO_ROW_LIMIT;
			queryArgs[ROWBOUNDS_INDEX] = new RowBounds(offset, limit);
			
			BoundSql newBoundSql = modifyBoundSqlSQLStatement(boundSql, sql,
					ms.getConfiguration());

			MappedStatement newMs = copyFromMappedStatement(ms,
					new BoundSqlSqlSource(newBoundSql), false);
			queryArgs[MAPPED_STATEMENT_INDEX] = newMs;
			if(logger.isDebugEnabled()) {
				logger.debug("sql = \n" + sql);
			}
		} else if(parameter instanceof CountParameter) {
			//获取总数
			parameter = ((CountParameter) parameter).getParameter();
			BoundSql boundSql = ms.getBoundSql(parameter);
			String sql = boundSql.getSql().trim().toLowerCase();
			sql = sql.replaceAll("	", " ");
			if(sql.indexOf("group")!=-1 || sql.indexOf("order")!=1){
				sql = "select count(1) from (" + sql + ") tmp"; //性能差
			}else{
				sql=sql.replaceAll("^select *(?:(?!select|from)[\\s\\S])*(\\( *select *(?:(?!from)[\\s\\S])* *from *[^\\)]*\\)(?:(?!select|from)[\\s\\S])*)*(?:(?!select|from)[\\s\\S])*from", "select count(*) from");
			}
			
			BoundSql newBoundSql = modifyBoundSqlSQLStatement(boundSql, sql,
					ms.getConfiguration());
			
			MappedStatement newMs = copyFromMappedStatement(ms,
					new BoundSqlSqlSource(newBoundSql), true);
			queryArgs[MAPPED_STATEMENT_INDEX] = newMs;
			queryArgs[PARAMETER_INDEX] = parameter;
			if(logger.isDebugEnabled()) {
				logger.debug("sql = \n" + sql);
			}
		}
	}
	
	/**
	 * 修改BoundSql实例， sql语句
	 * @param boundSql
	 * @param sql
	 * @param configuration
	 * @return
	 */
	private BoundSql modifyBoundSqlSQLStatement(BoundSql boundSql, String sql,
			Configuration configuration) {
		try {
			FieldUtils.writeDeclaredField(boundSql, "sql", sql, true);
			return boundSql;
		} catch (IllegalAccessException e) {
			BoundSql newBoundSql = new BoundSql(configuration, sql,
					boundSql.getParameterMappings(),
					boundSql.getParameterObject());
			try {
				@SuppressWarnings("unchecked")
				Map<String, Object> additionalParameters = (Map<String, Object>) FieldUtils
						.readDeclaredField(boundSql, "additionalParameters",
								true);
				if (additionalParameters != null) {
					for (Map.Entry<String, Object> entry : additionalParameters.entrySet()) {
						newBoundSql.setAdditionalParameter(entry.getKey(), entry.getValue());
					}
				}
			} catch (Exception e1) {
			}
			return newBoundSql;
		}
	}

	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	public void setProperties(Properties properties) {
		String dialectClass = properties.getProperty("dialectClass");
		try {
			dialect = (Dialect) Class.forName(dialectClass).newInstance();
		} catch (Exception e) {
			throw new RuntimeException(
					"cannot create dialect instance by dialectClass:"
							+ dialectClass, e);
		}
	}

	public static class CountParameter {
		private Object parameter;
		public CountParameter(Object parameter) {
			this.parameter = parameter;
		}
		public Object getParameter() {
			return parameter;
		}
	}

}
