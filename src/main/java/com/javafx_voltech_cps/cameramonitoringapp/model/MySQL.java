package com.javafx_voltech_cps.cameramonitoringapp.model;

import java.util.List;

public interface MySQL<T> {

	boolean add(T item);

	boolean edit(T item);

	boolean delete(T item);

	List<T> getAll();
}
