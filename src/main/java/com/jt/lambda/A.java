package com.jt.lambda;

class A {
    public void methodA(B b) {
        System.out.println("methodA is invoked");
        b.methodB();
    }
}

class B {
    public void methodB() {
        System.out.println("methodB is invoked");
    }
}