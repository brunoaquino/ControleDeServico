package br.com.cs.controller;

import java.io.Serializable;

public class SimpleClass implements Serializable {

	private static final long serialVersionUID = 1L;

	public void doSomething() {
		System.out.println("Consider it done");
	}

	// @Produces
	// public SimpleClass generateSimpleClass() {
	// return new SimpleClass();
	// }
}
