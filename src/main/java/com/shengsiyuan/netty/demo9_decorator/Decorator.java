package com.shengsiyuan.netty.demo9_decorator;

public class Decorator implements Component {

    private Component component;

    public Decorator(Component component) {
        this.component = component;
    }

    public void doSomething() {
        component.doSomething();
    }

}
