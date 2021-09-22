package by.training.task6.dao;

public interface Specification<T> {
    boolean specified(T t);
}
