package by.training.task6.dao;

import java.util.function.Predicate;

public interface Specification<T> {
    boolean specified(T t);
}
