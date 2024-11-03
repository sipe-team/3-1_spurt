# JVM 과 JIT Compiler 그리고 Warm-up

---

# 1. JVM 이란?

> Java 를 동작시키려면 Java의 엔진과 시스템 라이브러리들이 필요하다. 이것이 JVM 이다.
Java Virtual Machine의 약자로 OS에 맞는 Java 런타임 환경을 제공하는 가상머신이다.
> 

![image.png](/.asset/kwj1270/week1-12.png)

일반적으로 JVM 은 아래와 같은 특징을 가지고 있다.

- JAVA와 OS 사이의 **중개자 역할**을 수행하여 **JAVA가 OS에 종속되지 않고 사용할 수 있도록 해준다.**
- 상당히 안전한 메모리 관리 시스템을 갖춘 덕에 메모리 누수 문제와 엉뚱한 메모리 참조 문제를 피한다.
- 런타임에 핫코드를 감지, 컴파일하고 최적화하여 자바 애플리케이션이 최상의 성능을 내도록 도와준다.

```
💡
개발자들이 JVM 이란 단어를 들었을 때 위와 같은 특징을 가지고 있다고 생각한다.
하지만 JVM 이란 것은 단순히 기준점일 뿐이며, 이들을 구현하는 구현체마다 조금씩 다른점이 있다.

일반적으로 우리가 생각하는 JVM 은, Oracle JDK HotSpot VM 이다.
하지만 이전에는, Sun Classic VM, Exact VM 등등이 있었으며 기타 여러 VM 들이 있다.
이번 아티클에서 우리가 알아보고자 하는 것은 Oracle JDK HotSpot VM 이다. 

최근에 들어봤을 수 있는데 GraalVM 이 VM 구현체 중 하나이다.
```

## 1.1. Write once, run anywhere

![image.png](/.asset/kwj1270/week1-1.png)

**플랫폼 종속성**

1. 기계어가 CPU마다 다르다.
2. 운영체제 API가 서로 다르다.
3. 운영체제마다 실행 파일 형식이 다르다.

JVM이 등장하기 이전에는 **플랫폼 종속성으로 인해 OS마다 명령어를 해석하는 방법이 달랐다.**
즉, 같은 소스 코드라도 **OS마다 명령어를 해석하는 방법이 달라 이에 따른 추가 작업을 진행해줘야 했다.**
하지만, JVM이 등장하면서 소스 코드와 기계어의 중간 단계인 **`ByteCode`를 생성하고
컴퓨터의 OS를 파악해 바이트코드를 알맞는 기계어로 변환하는 작업을 진행한다.**

즉, `ByteCode`만 존재한다면 우리는 어떠한 OS 환경에서도 프로그램을 구동시킬 수 있다.
이러한 JVM의 특징을 `WriteOnce,RunAnywhere(WORA)` 
또는 `WriteOnce,RunEverywhere(WORE)`이라 부른다.

추가로, `Kotlin`과 `Java` 소스 코드가 상호 운용이 가능한 이유도
`Kotlin`으로 작성한 내용이 결국에 JVM에 의해 `ByteCode`로 변환되기 때문이다.
즉, 이전 소스코드가 무엇이었든간에 JVM에서 `ByteCode`를 해석해서 명령을 내리기 때문이다.

## 1.2. ByteCode

> 특정 하드웨어가 아닌 VM에서 돌아가는 실행 프로그램을 위한 이진법으로 표현된 코드
> 

![image.png](/.asset/kwj1270/week1-2.png)


하나의 명령어의 크기가 1Byte(8bit)라서 ByteCode라고 부른다.
하드웨어가 아닌 소프트웨어(VM)에 의해 처리되기 때문에, 기계어보다 추상적이며 
특정 하드웨어의 기계 코드를 만드는 컴파일러의 입력으로 사용되거나, 가상 컴퓨터에서 명령어를 바로 실행한다.
결과적으로 특정 하드웨어에 대한 기계어를 사용하지 않아도 되므로 의존성을 줄인다.

---

Java에서의 `ByteCode`는 JVM이 이해할 수 있는 언어로 변환된 `.class` 파일을 의미한다.
이러한 `.class` 파일들은 `.java` 파일로 **역컴파일**할 수 있으며 이를 통해
개발자들은 컴파일 타임에서 발생하는 여러 작업과 변경사항을 눈으로 확인할 수 있다.

- CLI 기준 : `javap -c FQCN_(.class 파일)`

![image.png](/.asset/kwj1270/week1-3.png)

- Mac + IntelliJ 기준 :
    1. 소스코드에 커서를 위치시킨다.
    2. `command + shift + a`를 누른다.
    3. Actions 탭에서 `Show Bytecode`를 입력후 누른다.

![image.png](/.asset/kwj1270/week1-4.png)

# 2. JIT Compiler 와 컴파일러

## 2.1. JVM 동작 과정과 구성 요소

![image.png](/.asset/kwj1270/week1-5.png)

**JVM 동작 과정**

1. JVM은 OS로부터 프로그램이 필요로 하는 메모리를 할당받는다.
2. JVM은 메모리를 용도에 따라 여러 영역으로 나누어 관리한다.
3. 자바 컴파일러가 자바 소스코드를 읽어들여 자바 바이트코드로 변환시킨다.
4. Class Loader를 통해 class 파일들을 JVM으로 로딩한다.
5. 로딩된 class 파일들은 Execution engine을 통해 해석된다.
6. 해석된 바이트코드는 Runtime Data Areas에 배치되어 수행이 이루어진다.
7. 실행 과정 속에서 JVM은 필요에 따라
Thread Synchronization과 GC같은 관리작업을 수행한다.

**JVM 구성 요소**

- Java source : 사용자가 정의한 자바 파일
- Compiler : 자바를 클래스 파일로 변환
- ByteCode : 클래스 파일들
- ClassLoader : 클래스 파일들을 JVM으로 로드 및 링크하여 RuntimeDataArea에 배치
- ExecutionEngine : 로딩된 클래스 파일들의 바이트코드를 해석하여 JVM에서 실행가능하도록 한다.
    - Interpreter : 자바 바이트코드를 명령어 단위로 읽고 해석하는 역할
    - JIT Compiler : 자바 바이트코드를 기계어로 변환시키는 역할
    - Garbage Collect : 더 이상 사용되지 않는 메모리를 해제해주는 역할
- RuntimeDataArea : JVM이라는 프로세스가 프로그램을 수행하기 위하여 OS로부터 받은 메모리 공간

## 2.2. Execution Engine (실행 엔진)

![image.png](/.asset/kwj1270/week1-6.png)

![image.png](/.asset/kwj1270/week1-7.png)

- Execution Engine 이란, 로드된 클래스 파일의 바이트 코드를 실행하는 런타임 모듈이다.
- 바이트 코드의 유효성(보안, 변조)을 검사한 뒤, 코드를 분석하여 bottleneck 또는 HotSpot 을 탐지한다.
- HotSpot 가상 머신
    - 코드를 컴파일 하는 방법을 최적화 하기 위해 라인별 adaptive compiler를 사용한다.
    - 성능 향상을 위해 코드의 성능에 중요한 부분을 컴파일
    거의 사용되지 않는 코드(대부분의 어플리케이션)는 컴파일하지 않는다.

HotSpot 가상머신은 
라인별로 바이트 코드를 읽어 기계어로 변환해 실행하며 **기본적으로는 인터프리터를 통해 실행을 하지만** 
**자주 등장하는 바이트 코드일 경우 JIT 컴파일러를 사용해 컴파일을 하는 방법을 통해 실행 방법을 최적화 시킨다.** 자세한 내용은 [Java Virtual Machine Guide](https://docs.oracle.com/en/java/javase/11/vm/java-virtual-machine-technology-overview.html#GUID-982B244A-9B01-479A-8651-CB6475019281) 을 참고

```
🌻
자바 Execution Engine 이 어떻게 동작하는 지는 JVM 명세에 작성되어 있지 않다. 
”””
명령 실행에 대한 구체적인 사항은 
구현자의 창의력을 저해시킬 수 있기 때문에 JVM에 명시하지 않겠다.
“””
위와 같이 적혀있기에, 자바 실행 엔진은 JVM 종류마다 다름을 알 수 있다.
```

### 2.2.1. Interpreter

- 바이트코드 명령어를 하나씩 읽어서 해석하고 실행한다.
- 하나씩 해석하고 실행하기 때문에 바이트코드 하나하나의 해석은 빠른 대신 
인터프리팅 결과의 실행은 느리다는 단점을 가지고 있다.
- 흔히 얘기하는 인터프리터 언어의 단점을 그대로 가지는 것이다.
- 즉, 바이트코드라는 '언어'는 기본적으로 인터프리터 방식으로 동작한다.

### 2.2.2. JIT Compiler (Just - in - time)

- 인터프리터의 단점을 보완하기 위해 도입된 것이 JIT 컴파일러이다.
- **적절한 시점에 바이트코드 전체를 컴파일하여 네이티브 코드로 변경하고, 
이후에는 해당 메서드를 더 이상 인터프리팅하지 않고 네이티브 코드로 직접 실행하는 방식이다.**
- 네이티브 코드를 실행하는 것이 하나씩 인터프리팅하는 것보다 빠르고, 
**네이티브 코드는 캐시에 보관하기 때문에 한 번 컴파일된 코드는 계속 빠르게 수행되게 된다.**
- 런타임시에 필요에 따라 바이트코드를 
기계코드로 번역해 주는 컴파일러로 Dynamic Translation이라고도 부른다.

```
💡
**JIT 컴파일러 동작 기준과 원리**
JIT Compiler가 컴파일하는 과정은 바이트 코드를 인터프리팅하는 것보다 훨씬 오래 걸린다.
그렇기 때문에 **한 번만 실행되는 코드라면 JIT Compiler 대신, 인터프리팅으로 처리를 한다**
반대로, 반복/중복으로 발생하는 바이트 코드가 있다면 JVM에서 얼마나 자주 수행되는지 체크하고,
**일정 기준을 넘는다면 `JIT Compiler`가 `ByteCode`를 기계어로 변환하고 Caching 해놓는다.**

이후, 같은 코드가 나온다면 **다시 번역할 필요 없이 Caching 한 해당 기계어를 가져와 사용한다.**
참고로, `Caching`의 위치는 `JVM`안의 **`CodeCache`에 들어간다.**
```

![image.png](/.asset/kwj1270/week1-8.png)

Oracle의 JDK 구현은 오픈소스 OpenJDK 프로젝트를 기반으로 한다. 
여기에는 Java 버전 1.3부터 사용 가능한 **HotSpot 가상 머신이 포함된다. 
HotSpot 가상 머신에는 두 개의 ‘JIT 컴파일러 모드’가 포함된다 
클라이언트 컴파일러(C1이라고도 함)와 서버 컴파일러(opto 또는 C2라고 함)이다**.

- 클라이언트 컴파일(c1)
    - 바이트코드로부터 최대한 많은 정보를 뽑아내어 실제 동작하는 코드 블럭에 대해 최적화를 집중
        - 전체적인 최적화에는 큰 관심이 없다.
        - 더 빠르게 실행되고 덜 최적화된 코드를 생성하도록 설계
        - 주로 프로그램의 시작시간을 최소화하는데에 집중한다.
    - JIT 컴파일을 위해 긴 일시 정지를 원하지 않기 때문에 데스크톱 애플리케이션에 더 적합
- 서버 컴파일러(c2)
    - 부분적인 코드 실행보다 전체적인 성능 최적화에 관점을 둔다.
        - 약간 더 많은 시간이 걸리지만 더 잘 최적화된 코드를 생성
    - 컴파일에 더 많은 시간을 할애할 수 있는 장기 실행 서버 애플리케이션에 더 적합하다.

```
💡
C1/C2 확장성(Scalability)
컴퓨터의 CPU와 메모리 사양에 따라 알아서 jvm의 모드가 선택된다. 
server mode를 사용할지 client 모드를 사용할지, 
혹은  어느정도의 heap memory를 설정 할지등을 고민을 하지 않아도 된다. 
```

### 2.2.3. Java Compiler 기술(1) - Hot Spot Detection / Hot Code Detection

JIT 컴파일러를 동작시키는 기술로, JVM이 바이코드들을 해석하다가
루프 등을 만나 **중복적인 해석이 이루어진다고 판단되면 바이트 코드를 기계어로 직접 컴파일하는 방식이다.** 
동적으로 로딩하는 코드에 비해서
전체 코드를 컴파일 하는 방식에 비해 컴파일 시간이 적고 메모리 공간도 더 효율적으로 소모한다.


```
💡
만약 중복된 코드가 아닌 모든 코드를 컴파일하면 안되는 걸까? 🤔
모든 코드를 컴파일하는 방식의 수행 시간 자체는 빠르지만
프로그램 크기가 커지고 기기별 이식성이 떨어진다는 문제점이 있다.
그렇기에 JVM에서는 기본으로 `Interpreter` 방식을 사용하지만, 
여러 번의 중복이 발생하는 경우에만 `Hot Spot Detection` 방식을 사용한다.
```

### 2.2.4. Java Compiler 기술(2) - Method inlining

Hotspot은 중복되는 코드 호출에 대해 컴파일을 수행할뿐 아니라, 
클래스안에서 사용된 다른 클래스에 대해 method inlining을 수행함으로서 
**다른 메모리 공간에 있는 메소드에 대해 호출하는 것을 피할수 있다.** 
특히 자바에서는 동적으로 계속해서 메소드를 깊숙한 레벨로 inlining하도록 되어있다.

- 메소드 호출(Method call)을 하지 않는다.
- 동적인 디스패치(Dynamic dispatch)를 하지 않는다.
- 값에 대한 상수 연산이 가능하다. 
(constant-fold; 상수처럼 인식되어 바로 연산할 수 있도록 하기 때문에 상수 연산으로 번역한다.)
    - “`a.foo()+2”` 의 결과값은 “5”
    - 런타임(Runtime)시 `a.foo() 메소드를` 실행하지 않고 바로 “3 + 2” 연산으로 처리

JIT 컴파일러에서 수행하는 최적화 방법으로
자주 실행되는 메서드의 호출을 본문으로 대체하여 **런타임시에 컴파일된 소스 코드를 최적화**하는 방법이다.

```java
public void testAddPlusOne() {
  int v1 = addPlusOne(2, 5);
  int v2 = addPlusOne(7, 13);

}
public int addPlusOne(int a, int b) {
  return a + b + 1;
}

```

```java
public void testAddPlusOne() {
  int v1 = 2 + 5 + 1;
  int v2 = 7 + 13 + 1
}

```

JIT 컴파일러는 `Method inlining`를 통해 메서드 호출의 [오버헤드](https://ko.wikipedia.org/wiki/%EC%98%A4%EB%B2%84%ED%97%A4%EB%93%9C)를 피할 수 있다.

### 2.2.5. Compiler 기술(3) - **Dynamic Deoptimization (동적 최적화 취소)**

Class를 동적으로 로딩하는 과정은 그렇게 어려워보이지만,  
컴파일러 최적화의 관점에서 볼때는 매우 어려운일이다. 

사실 동적으로 클래스간의 관계가 맺어지는 자바같은 동적 OOP프로그램에서는 
특정 시점에서 method inlining을 하는 것이 장기적으로 볼땐 최선의 선택이 아닐수도 있다. 
어떨때는 Method Inlining을 취소하거나 다시 최적화를 수행하는 경우도 있다. 
이렇게 최적화를 자유롭게 취소하고 재 최적화를 할수 있는 방법이 HotSpot에는 포함되어있다

---

# 3. JVM **Warm-up**

JVM 프로세스는 Lazy Loading 방식을 추구하고 있다.
애플리케이션이 시작될 때 클래스 파일들을 읽는 것이 아니라, 
클래스가 필요한 시점까지 로딩을 지연 했다가 클래스 파일에 대한 첫 호출시 읽는 것을 의미한다.
**그렇다면, 트래픽이 많은 서비스에 새로운 배포가 진행되면 어떤 현상이 벌어질까?**   

![image.png](/.asset/kwj1270/week1-14.png)

TPS 그래프를 보면, **TPS는 일정한데 불구하고 배포 직후 평상시 대비 큰 지연이 발생한 것**을 볼 수 있다.
즉, 배포가 이루어진 이후의 초기 요청들에 대해서, 지연이 발생해 빠른 응답을 내려주지 못하게 되는 것이다.   

<aside>
💡

Class Loader 
애플리케이션이 시작될 때 클래스 파일들을 읽는 것이 아니라, 
클래스가 필요한 시점까지 로딩을 지연 했다가 클래스 파일에 대한 첫 호출시 읽는 것을 의미한다.
클래스를 로딩에 대해서도 살펴보면 좋은데 내용이 길어지므로 해당 아티클에서는 다루지 않는다.

</aside>

---

## 3.1. JVM **Warm-up** 관점에서 다시보는 JIT Compiler

JIT Compiler 는 애플리케이션 **실행 중 동적으로 바이트 코드를 기계어로 컴파일한다.** 
이렇게 컴파일된 코드를 실행하게 되면, 
인터프리터가 바이트코드를 실행하는 것 보다 훨씬 빠른 속도로 실행이 가능하다.

하지만, 모든 바이트 코드를 기계어로 번역을 한다면 애플리케이션 기동 시간이 매우 길어질 것이다.
따라서 애플리케이션 실행 시간과 최적화 사이의 밸런스를 잘 맞춰야한다.

JIT 컴파일러는 애플리케이션에서 **자주 실행된다고 판단되는 특정 부분만을 기계어로 컴파일한다.**
**자주 실행된다고 판단되는 특정 부분**을 **핫스팟(Hotspot)**이라고 부른다.

JIT 컴파일러는 **프로파일링을 통해,** 실행중인 애플리케이션의 동작을 분석하고 
코드 실행 횟수, 루프 반복 횟수, 메소드 호출 등의 정보를 측정하고 기록한다.
JIT 컴파일러는 프로파일링 결과를 토대로 핫스팟을 식별한다. 
핫스팟이 식별되었다면, JIT 컴파일러는 **메소드 단위로 바이트 코드를 기계어로 번역한다.**

JIT 컴파일러는 이렇게 번역된 기계어를 **코드 캐시(Code Cache)**라는 캐시 공간에 저장한다. 
코드 캐시에 기계어를 저장하면, 핫스팟으로 판단된 코드는 다시 컴파일하지 않고, 
코드 캐시에서 꺼내어 사용할 수 있으므로 성능 향상을 이루어낼 수 있다.  

## 3.2. JIT Compiler **Tiered Compilation**

> **Tiered Compilation:** 컴파일 과정이 최적화 수준에 따라 복수 개의 단계로 나눠진 것
> 

### 3.2.1. 컴파일러의 종류(**C1 컴파일러와 C2 컴파일러)**

![image.png](/.asset/kwj1270/week1-13.png)

- **C1 컴파일러는 가능한 빠른 실행 속도를 위해 코드를 가능한 빠르게 최적화하고 컴파일한다.**
    - 특정 메서드가 C1 컴파일러의 임계치 설정 이상으로 호출되면, 
    해당 메서드의 코드는 C1 컴파일러를 통해 제한된 수준으로 최적화된다.
    그리고 컴파일된 기계어는 코드 캐시에 저장된다.
- **C2 컴파일러는 C1 컴파일러보다 더 높은 수준의 최적화를 수행한다.**
    - C1 컴파일러 이후 메서드가 C2 컴파일러의 임계치 설정보다 많이 호출되면, 
    코드는 C2 컴파일러에 의해 최적화되고 컴파일된다.
    - 최적화와 컴파일이 끝나면 마찬가지로 코드 캐시에 기계어를 저장한다.

### 3.2.2. **JIT Tiered Compilation**

JIT Tiered Compilation 은 **인터프리터**, **C1 컴파일러**, **C2 컴파일러**를 통해 5가지 Level로 나뉜다. 
Level 0은 인터프리터, Level 1 ~ 3은 C1 컴파일러, Level 4는 C2 컴파일러에 의해 수행된다.

- **Level 0 - Interpreted Code**: 
JVM은 초기에 모든 코드를 인터프리터를 통해 실행한다. 
이 단계는 앞서 살펴본것과 같이, 컴파일된 기계어를 실행하는 것보다 성능이 낮다.
- **Level 1 - Simple C1 Compiled Code**: 
Level 1 은 JIT 컴파일러가 단순하다고 판단한 메서드에 대해 사용된다. 
여기서 컴파일된 메서드들은 복잡도가 낮아, C2 컴파일러로 컴파일한다고 하더라도 성능이 향상되지 않는다. 따라서 추가적인 최적화가 필요 없으므로 프로파일링 정보도 수집하지 않는다.
- **Level 2 - Limited C1 Compiled Code**: 
제한된 수준으로 프로파일링과 최적화를 진행하는 단계이다. C2 컴파일러 큐가 꽉 찬경우 실행된다.
- **Level 3 - Full C1 Compiled Code**: 
최대 수준으로 프로파일링과 최적화를 진행한다. 즉 일반적인 상황에서 수행된다.
- **Level 4 - C2 Compiled Code**: 
애플리케이션의 장기적인 성능을 위해 C2 컴파일러가 최적화를 수행한다. 
Level 4에서 최적화된 코드는 완전히 최적화 되었다고 간주되어, 더이상 프로파일링 정보를 수집하지 않는다.

서버가 배포된 직후에는 **JIT 컴파일러는 아무런 코드도 기계어로 컴파일 하지 않고 있을 것이다.**
따라서 코드 캐시에 적재된 기계어도 존재하지 않는다. 
따라서 배포 직후 시점의 코드는 인터프리터에서 실행되거나, 
C1 혹은 C2 컴파일러가 최적화하고 컴파일 하는 과정이 동반되므로 필연적으로 성능 저하가 발생할 수 밖에 없다. 

## 3.3. 그래서 **JVM Warm-up 이란?**

앞서 JVM 의 동작 방식이자 병목 지점에 대해서 아래와 같이 정리해볼 수 있다.

- **‘클래스는 필요할 때 Lazy Loading 으로 메모리에 적재된다’**
- **‘JVM은 자주 실행 되는 코드를 컴파일하고 캐시한다’**,

Latency의 원인은 **클래스가 메모리에 적재되지 않았고, 코드가 최적화된 기계어로 컴파일되지 않았기 때문이다.** 
반대로 Latency 를 해결하려면 **클래스를 메모리에 적재하고, 코드를 최적화하여 컴파일 하면 된다.
이처럼,** JVM Warm-up 이란, JVM 의 최대의 성능을 끌어올리기 위해 워밍업을 진행하는 것이다.

이를 적용하기 위해서는 
**애플리케이션이 기동되는 시점에, 자주 호출될 것으로 예상되는 지점의 코드를 충분히 많이 실행하면 된다.**   

**웜업 전**

![image.png](/.asset/kwj1270/week1-9.png)


**웜업 후**

![image.png](/.asset/kwj1270/week1-10.png)

## 3.3. Warm-up 방법

### 3.3.1. 예시 코드를 통해 살펴보기

```kotlin
// 웜업 인터페이스 선언
interface Warmer {
    suspend fun run()
 
    val isDone: Boolean
}
 
// 웜업을 한 번만 실행하도록 강제하는 클래스
abstract class ExactlyOnceRunWarmer : Warmer {
    override var isDone = false
    private val mutex = Mutex()
 
    override suspend fun run() {
        if (!isDone && mutex.tryLock()) {
            try {
                doRun()
                setDone()
            } finally {
                mutex.unlock()
            }
        }
    }
 
    protected fun setDone() {
        this.isDone = true
    }
 
    abstract suspend fun doRun()
}
 
// 구현 예시
@Component
class Example1 : ExactlyOnceRunWarmer() {
    override suspend fun doRun() {
        delay(1_000) // 실제로 웜업을 위한 코드를 작성하면 된다.
    }
}
 
// 구현 예시
@Component
class Example2 : ExactlyOnceRunWarmer() {
    override suspend fun doRun() {
        delay(5_000)
    }
}
 
// 구현 예시
@Primary
@Component
class CompositeWarmer(
    private val warmers: Collection<Warmer>,
) : ExactlyOnceRunWarmer() {
    override suspend fun doRun() {
        withContext(NonCancellable) { // 이미 실행된 워머는 취소하지 않는다.
            warmers.map {
                async { it.run() }
            }.awaitAll()
 
            if (warmers.all { it.isDone }) {
                setDone()
            }
        }
    }
}
```

- 워머를 생성하여, 트래픽이 많이 몰리는 코드에 대해서 반복적으로 실행할 수 있도록 한다.
- 코드의 경우도 실제 비즈니스에 영향을 주지 않게끔 작성되어 있어야한다.
- 결과
    - 클래스 로드 사전 진행
    - 프로파일링 정보 사전 생성
    - I/O 연결 설정 사전 진행

### 3.3.2. TCP Connection 에 대해서

엔터프라이즈 애플리케이션을 개발하다보면, 자연스레 외부 시스템과의 연동은 필수적이다.

- DB
- Meessage Queue
- Othre Server

앞서 언급했던 JVM 의 경우 Lazy Loading 이 기본 설정이기 때문에, 
이들과 TCP Connection 을 맺는 역할을 담당하는 클래스 및 IO 과정도 활성화 되어있지 않다.
이를 위해서 웜업 과정에서 해당 외부 시스템들에 대해서 네트워크 통신 과정도 넣으면 좋다.

### 3.3.3. 실제 요청 수신

![image.png](/.asset/kwj1270/week1-11.png)

```kotlin
// AbstractReactiveHealthIndicator를 상속해 웜업 상태 정보를 제공한다.
@Component
class WarmupHealthIndicator(
    private val warmer: Warmer,
) : AbstractReactiveHealthIndicator() {
 
    override fun doHealthCheck(builder: Health.Builder): Mono<Health> {
        return mono {
            warmer.run() // 편의상 실행과 검사를 인디케이터에서 처리한다.
            val health = builder.also {
                if (warmer.isDone()) { // 웜업이 완료되면 상태를 UP으로 노출한다.
                    it.up()
                } else { // 웜업이 아직 완료되지 않았으면 상태를 DOWN으로 노출한다.
                    it.down()
                }
            }.build()
 
            return@mono health
        }
    }
}
```

```kotlin
management:
  endpoint:
    health:
      group:
        readiness:
          show-components: always
        liveness:
          show-components: always
          exclude:
            - warmup # 활성 그룹에서 warmup HealthIndicator를 제외한다.
```

- k8s 의 경우 `readinessProbe` 를 통해 애플리케이션의 엔드포인트 활성 상태를 판단 가능
- Spring Boot는 [애플리케이션 가용성을 진단](https://docs.spring.io/spring-boot/docs/current/reference/html/features.html#features.spring-application.application-availability)하고, [Kubernetes 환경을 감지](https://docs.spring.io/spring-boot/docs/current/reference/html/deployment.html#deployment.cloud.kubernetes)해 
[프로브를 위한 진단 기능을 HTTP API로 노출](https://docs.spring.io/spring-boot/docs/current/reference/html/actuator.html#actuator.endpoints.kubernetes-probes)하는 것까지 지원하고 있기 때문에 
비교적 쉽게 진행할 수 있다.
(물론 웜업 상태를 검사하는 기능을 통합하기 위해서는 약간의 수고가 더 필요하긴 한다.)
- Spring Actuator 설정을 통해 준비성 상태 진단 기능에 웜업 상태 진단 기능을 통합한다.
- [https://engineering.linecorp.com/ko/blog/apply-warm-up-in-spring-boot-and-kubernetes](https://engineering.linecorp.com/ko/blog/apply-warm-up-in-spring-boot-and-kubernetes)