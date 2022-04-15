package com.lovethefeel.designpattern.strucural.proxy._03_java;

import com.lovethefeel.designpattern.strucural.proxy._02_after.interfaces.DefaultGameService;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyInJava {
    public static void main(String[] args) {
        ProxyInJava proxy = new ProxyInJava();
        proxy.dynamicProxy();
    }

    private void dynamicProxy() {
        GameService gameServiceProxy = getGameServiceProxy(new DefaultGameService());
        gameServiceProxy.startGame();
    }

    private GameService getGameServiceProxy(DefaultGameService target) {
        return (GameService) Proxy.newProxyInstance(this.getClass().getClassLoader(),
                new Class[]{GameService.class}, new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("Hello dynamic proxy");
                        // 메소드를 실행하기 위한
                        // target : 어떤 클래스를 실행할것인지
                        // args : 필요한 인자값
                        method.invoke(target, args);
                        return null;
                    }
                });
    }
}
