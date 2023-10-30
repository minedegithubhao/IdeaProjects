package org.example.tiaoliao;

import org.example.yinliao.Beverage;

public abstract class CondimentDecorator extends Beverage {
	Beverage beverage;
	public abstract String getDescription();
}
