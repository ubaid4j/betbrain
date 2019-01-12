package com.ubaid.app.model.dao;

import java.util.LinkedList;

import com.ubaid.app.model.abstractFactory.AbstractFactory;
import com.ubaid.app.model.abstractFactory.OutcomeBuilderFactory;
import com.ubaid.app.model.builder.resultSetBuilder.ResultSetBuilder;
import com.ubaid.app.model.objects.Entity;

public interface OutcomeDAO
{
	AbstractFactory factory = new OutcomeBuilderFactory();
	ResultSetBuilder builder = (ResultSetBuilder) factory.getBuilder();
	LinkedList<Entity> getAll(Object[] ids);
}

