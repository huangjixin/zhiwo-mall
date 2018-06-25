package com.fulan.application.orm.tenant;

import java.util.List;

import com.baomidou.mybatisplus.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.plugins.parser.AbstractJsqlParser;
import com.fulan.application.context.ContextHolder;
import com.fulan.application.context.GlobalContext;

import net.sf.jsqlparser.expression.BinaryExpression;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.LongValue;
import net.sf.jsqlparser.expression.StringValue;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.expression.operators.relational.EqualsTo;
import net.sf.jsqlparser.expression.operators.relational.ExpressionList;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.delete.Delete;
import net.sf.jsqlparser.statement.insert.Insert;
import net.sf.jsqlparser.statement.select.FromItem;
import net.sf.jsqlparser.statement.select.Join;
import net.sf.jsqlparser.statement.select.LateralSubSelect;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.SelectBody;
import net.sf.jsqlparser.statement.select.SetOperationList;
import net.sf.jsqlparser.statement.select.SubJoin;
import net.sf.jsqlparser.statement.select.SubSelect;
import net.sf.jsqlparser.statement.select.ValuesList;
import net.sf.jsqlparser.statement.select.WithItem;
import net.sf.jsqlparser.statement.update.Update;

public class TenantSqlParser extends AbstractJsqlParser {

	@Override
	public void processSelectBody(SelectBody selectBody) {
		if (selectBody instanceof PlainSelect) {
			processPlainSelect((PlainSelect) selectBody);
		} else if (selectBody instanceof WithItem) {
			WithItem withItem = (WithItem) selectBody;
			if (withItem.getSelectBody() != null) {
				processSelectBody(withItem.getSelectBody());
			}
		} else {
			SetOperationList operationList = (SetOperationList) selectBody;
			if (operationList.getSelects() != null && operationList.getSelects().size() > 0) {
				List<SelectBody> plainSelects = operationList.getSelects();
				for (SelectBody plainSelect : plainSelects) {
					processSelectBody(plainSelect);
				}
			}
		}
	}

	/**
	 * <p>
	 * insert 璇彞澶勭悊
	 * </p>
	 */
	@Override
	public void processInsert(Insert insert) {
		TenantType tenantType = TenantEntityHolder.getTenantType(insert.getTable().getName());
		if (tenantType == null)
			return;

		if (TenantType.COMPANY.equals(tenantType)) {
			insert.getColumns().add(new Column(TenantInfo.COMPANY_COLUMN));
		} else if (TenantType.COLLEGE.equals(tenantType)) {
			insert.getColumns().add(new Column(TenantInfo.COMPANY_COLUMN));
			insert.getColumns().add(new Column(TenantInfo.COLLEGE_COLUMN));
		}

		if (insert.getSelect() != null) {
			processPlainSelect((PlainSelect) insert.getSelect().getSelectBody(), true);
		} else if (insert.getItemsList() != null) {
			List<Expression> expressions = ((ExpressionList) insert.getItemsList()).getExpressions();
			if (TenantType.COMPANY.equals(tenantType)) {
				expressions.add(new LongValue(ContextHolder.get().getCompanyId()));
			} else if (TenantType.COLLEGE.equals(tenantType)) {
				expressions.add(new LongValue(ContextHolder.get().getCompanyId()));
				expressions.add(new LongValue(ContextHolder.get().getCollegeId()));
			}

		} else {
			throw new MybatisPlusException(
					"Failed to process multiple-table update, please exclude the tableName or statementId");
		}
	}

	@Override
	public void processUpdate(Update update) {
		List<Table> tableList = update.getTables();
		if (null == tableList || tableList.size() >= 2) {
			throw new MybatisPlusException("Failed to process multiple-table update, please exclude the statementId");
		}

		Table table = tableList.get(0);
		TenantType tenantType = TenantEntityHolder.getTenantType(table.getName());
		if (tenantType == null)
			return;

		update.setWhere(this.andExpression(table, update.getWhere(), tenantType));
	}

	/**
	 * <p>
	 * delete 璇彞澶勭悊
	 * </p>
	 */
	@Override
	public void processDelete(Delete delete) {
		TenantType tenantType = TenantEntityHolder.getTenantType(delete.getTable().getName());
		if (tenantType == null)
			return;

		delete.setWhere(this.andExpression(delete.getTable(), delete.getWhere(), tenantType));
	}

	protected BinaryExpression andExpression(Table table, Expression where, TenantType tenantType) {

		if (null != where) {
			return new AndExpression(tenantExpression(table, tenantType, false),where);
		}

		return new AndExpression(tenantExpression(table, tenantType, true),where);
	}
	
	protected BinaryExpression tenantExpression(Table table,TenantType tenantType,boolean withAlias) {

		if (TenantType.COMPANY.equals(tenantType)) {
			GlobalContext ctx = ContextHolder.get();
			if(ctx == null) {
				EqualsTo equalsTo = new EqualsTo();
				equalsTo.setLeftExpression(new Column("1"));
				equalsTo.setRightExpression(new StringValue("1"));
				return equalsTo;
			}
				
			Long companyId = ContextHolder.get().getCompanyId();
			if(companyId == null){
				EqualsTo equalsTo = new EqualsTo();
				equalsTo.setLeftExpression(new Column("1"));
				equalsTo.setRightExpression(new StringValue("1"));
				return equalsTo;
			}
			
			EqualsTo equalsTo = new EqualsTo();
			equalsTo.setLeftExpression(this.getColumn(table, TenantInfo.COMPANY_COLUMN,withAlias));
			equalsTo.setRightExpression(new LongValue(companyId));
			return equalsTo;
		}
		
		EqualsTo equals1 = new EqualsTo();
		equals1.setLeftExpression(this.getColumn(table, TenantInfo.COMPANY_COLUMN,withAlias));
		equals1.setRightExpression(new LongValue(ContextHolder.get().getCompanyId()));
		EqualsTo equals2 = new EqualsTo();
		equals2.setLeftExpression(this.getColumn(table,TenantInfo.COLLEGE_COLUMN,withAlias));
		equals2.setRightExpression(new LongValue(ContextHolder.get().getCollegeId()));
		return new AndExpression(equals1, equals2);
	}

	protected void processPlainSelect(PlainSelect plainSelect) {
		processPlainSelect(plainSelect, false);
	}

	protected void processPlainSelect(PlainSelect plainSelect, boolean addColumn) {
		FromItem fromItem = plainSelect.getFromItem();
		if (fromItem instanceof Table) {
			Table fromTable = (Table) fromItem;
			TenantType tenantType = TenantEntityHolder.getTenantType(fromTable.getName());
			if (tenantType == null)
				return;
			
			plainSelect.setWhere(builderExpression(plainSelect.getWhere(), fromTable,tenantType));
		} else {
			processFromItem(fromItem);
		}
		List<Join> joins = plainSelect.getJoins();
		if (joins != null && joins.size() > 0) {
			for (Join join : joins) {
				processJoin(join);
				processFromItem(join.getRightItem());
			}
		}
	}

	/**
	 * 澶勭悊瀛愭煡璇㈢瓑
	 */
	protected void processFromItem(FromItem fromItem) {
		if (fromItem instanceof SubJoin) {
			SubJoin subJoin = (SubJoin) fromItem;
			if (subJoin.getJoin() != null) {
				processJoin(subJoin.getJoin());
			}
			if (subJoin.getLeft() != null) {
				processFromItem(subJoin.getLeft());
			}
		} else if (fromItem instanceof SubSelect) {
			SubSelect subSelect = (SubSelect) fromItem;
			if (subSelect.getSelectBody() != null) {
				processSelectBody(subSelect.getSelectBody());
			}
		} else if (fromItem instanceof ValuesList) {
			logger.debug("Perform a subquery, if you do not give us feedback");
		} else if (fromItem instanceof LateralSubSelect) {
			LateralSubSelect lateralSubSelect = (LateralSubSelect) fromItem;
			if (lateralSubSelect.getSubSelect() != null) {
				SubSelect subSelect = lateralSubSelect.getSubSelect();
				if (subSelect.getSelectBody() != null) {
					processSelectBody(subSelect.getSelectBody());
				}
			}
		}
	}

	/**
	 * 澶勭悊鑱旀帴璇彞
	 */
	protected void processJoin(Join join) {
		if (join.getRightItem() instanceof Table) {
			Table fromTable = (Table) join.getRightItem();
			TenantType tenantType = TenantEntityHolder.getTenantType(fromTable.getName());
			if (tenantType == null)
				return;
			
			join.setOnExpression(builderExpression(join.getOnExpression(), fromTable,tenantType));
		}
	}

	/**
	 * 澶勭悊鏉′欢
	 */
	protected Expression builderExpression(Expression expression, Table table,TenantType tenantType) {
        
		if (expression == null) {
			return tenantExpression(table, tenantType, false);
		} else {
			if (expression instanceof BinaryExpression) {
				BinaryExpression binaryExpression = (BinaryExpression) expression;
				if (binaryExpression.getLeftExpression() instanceof FromItem) {
					processFromItem((FromItem) binaryExpression.getLeftExpression());
				}
				if (binaryExpression.getRightExpression() instanceof FromItem) {
					processFromItem((FromItem) binaryExpression.getRightExpression());
				}
			}
			return new AndExpression(tenantExpression(table, tenantType, false), expression);
		}
	}

	protected Column getColumn(Table table, String columnName,boolean withAlias) {
		if(!withAlias)
			return new Column(columnName);
		
		if (null == table.getAlias()) {
			return new Column(columnName);
		}
		StringBuilder column = new StringBuilder();
		column.append(table.getAlias().getName());
		column.append(".");
		column.append(columnName);
		return new Column(column.toString());
	}

}
