/*
 * Hibernate, Relational Persistence for Idiomatic Java
 *
 * License: GNU Lesser General Public License (LGPL), version 2.1 or later.
 * See the lgpl.txt file in the root directory or http://www.gnu.org/licenses/lgpl-2.1.html.
 */
package org.hibernate.sql.model.ast;

import java.util.List;
import java.util.function.BiConsumer;

import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.sql.model.MutationOperation;

/**
 * Specialized TableMutation implementation for mutations which
 * define a where-clause
 *
 * @author Steve Ebersole
 */
public interface RestrictedTableMutation<O extends MutationOperation>
		extends TableMutation<O> {
	/**
	 * The bindings for each key restriction (WHERE clause).
	 */
	List<ColumnValueBinding> getKeyBindings();

	/**
	 * The number of {@linkplain #getKeyBindings() key bindings}
	 */
	default int getNumberOfKeyBindings() {
		return getKeyBindings().size();
	}

	/**
	 * Visit each {@linkplain #getKeyBindings() key binding}
	 */
	void forEachKeyBinding(BiConsumer<Integer,ColumnValueBinding> consumer);

	/**
	 * All optimistic-lock bindings (WHERE clause), appended after
	 * {@linkplain #getKeyBindings() key bindings}
	 *
	 * @see OptimisticLockType
	 */
	List<ColumnValueBinding> getOptimisticLockBindings();

	/**
	 * The number of {@linkplain #getOptimisticLockBindings() optimistic-lock bindings}
	 */

	default int getNumberOfOptimisticLockBindings() {
		final List<ColumnValueBinding> bindings = getOptimisticLockBindings();
		return bindings == null ? 0 : bindings.size();
	}

	/**
	 * Visit each {@linkplain #getOptimisticLockBindings() optimistic-lock binding}
	 */
	void forEachOptimisticLockBinding(BiConsumer<Integer,ColumnValueBinding> consumer);
}
