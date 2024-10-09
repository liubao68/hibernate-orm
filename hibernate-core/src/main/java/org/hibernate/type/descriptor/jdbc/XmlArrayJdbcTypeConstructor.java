/*
 * SPDX-License-Identifier: LGPL-2.1-or-later
 * Copyright Red Hat Inc. and Hibernate Authors
 */
package org.hibernate.type.descriptor.jdbc;


import org.hibernate.dialect.Dialect;
import org.hibernate.tool.schema.extract.spi.ColumnTypeInformation;
import org.hibernate.type.BasicType;
import org.hibernate.type.SqlTypes;
import org.hibernate.type.spi.TypeConfiguration;

/**
 * Factory for {@link XmlArrayJdbcType}.
 */
public class XmlArrayJdbcTypeConstructor implements JdbcTypeConstructor {
	public static final XmlArrayJdbcTypeConstructor INSTANCE = new XmlArrayJdbcTypeConstructor();

	public JdbcType resolveType(
			TypeConfiguration typeConfiguration,
			Dialect dialect,
			BasicType<?> elementType,
			ColumnTypeInformation columnTypeInformation) {
		return resolveType( typeConfiguration, dialect, elementType.getJdbcType(), columnTypeInformation );
	}

	public JdbcType resolveType(
			TypeConfiguration typeConfiguration,
			Dialect dialect,
			JdbcType elementType,
			ColumnTypeInformation columnTypeInformation) {
		return new XmlArrayJdbcType( elementType );
	}

	@Override
	public int getDefaultSqlTypeCode() {
		return SqlTypes.XML_ARRAY;
	}
}
