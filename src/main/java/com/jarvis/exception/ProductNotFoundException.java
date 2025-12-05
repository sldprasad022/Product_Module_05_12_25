package com.jarvis.exception;

public class ProductNotFoundException extends RuntimeException
{
	public ProductNotFoundException(String message)
	{
		super(message);
	}
}
