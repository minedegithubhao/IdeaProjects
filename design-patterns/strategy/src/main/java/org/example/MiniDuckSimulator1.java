package org.example;

import org.example.demo01.Duck;
import org.example.demo01.FlyRocketPowered;
import org.example.demo01.MallardDuck;
import org.example.demo01.ModelDuck;

public class MiniDuckSimulator1 {
 
	public static void main(String[] args) {
 
		Duck mallard = new MallardDuck();
		mallard.performQuack();
		mallard.performFly();
   
		Duck model = new ModelDuck();
		model.performFly();
		model.setFlyBehavior(new FlyRocketPowered());
		model.performFly();

	}
}
