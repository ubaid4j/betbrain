package com.ubaid.app.model.builder.resultSetBuilder;

import java.sql.ResultSet;
import java.util.LinkedList;

import com.ubaid.app.model.builder.Builder;
import com.ubaid.app.model.objects.Entity;


public interface ResultSetBuilder extends Builder
{
	LinkedList<Entity> build(ResultSet resultSet);
}
