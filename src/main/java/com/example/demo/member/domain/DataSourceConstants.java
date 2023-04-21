package com.example.demo.member.domain;

// 런타임 상수로는 열거형 등을 활용할 수 있지만,
// Annotation Processor 등을 위해서 제공하는 컴파일타임 상수는 다음처럼 제공 가능함.
// (단, 코드에 대한 종속성이 존재하므로 동적인 관리 대상이 아닌 경우 사용.)
// (즉, 변경을 반영하려면 반드시 재배포 하여야 한다는 점에 유념.)
public final class DataSourceConstants {

    public static final String DEFAULT_SCHEMA = "public";
    public static final String TB_MEMBER = "member";

    private DataSourceConstants() {}
}
