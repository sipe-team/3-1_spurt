# 서버 진단하기

# 1. 병목 지점 진단하기

서버를 운영하다 보면 아래와 같은 다양한 문제를 접할 수 있다.

- 서버가 hang 상태가 되는 경우
- 애플리케이션이 응답하지 않거나, 다양한 상황에서 예외를 발생하여 정상적인 동작을 하지 않는 경우
- 애플리케이션의 속도가 점점 느려지는 경우
- 특정 시간대, 특정 사용자, 특정 기능 등에 문제가 발생하는 경우

이러한 문제의 원인을 파악하기 위해, 전후 상황을 이해하고 문제의 원인을 분석하는 것이 필요하다.

서버는 크게 CPU, Network Interface Card (NIC), RAM, Disk Drives로 구성되며, 
각 자원은 여유 상태 또는 포화 상태를 가질 수 있다. 이를 파악하기 위한 상태 정보는 다음과 같다.

1. **요약:** 단위 시간 내 정보의 합계나 평균을 통해 대략적인 상태를 파악
   - 도구 예시: sar, vmstat

2. **이벤트 기록:** 이벤트가 순차적으로 기록되어 장애 상황에서의 문제 재현 시 사용
   - 예시: 패킷, 시스템 콜

3. **스냅샷:** 순간의 상태를 기록하여 문제 발생 시 원인 조사에 사용
   - 도구 예시: top

요약의 경우 명확한 파악이 어려우며, 
이벤트 기록의 경우 장애 상황에서 방대한 데이터를 다루기 때문에 문제 재현 시 주로 사용된다.

문제 발생 시에는 
로그와 요약 정보의 특정 수치에 알람을 설정하고, **스냅샷을 통해 원인을 파악하는 것이 효과적이다.**

---

# 2. USE 방법론

![image.png](/.asset/kwj1270/week1-21.png)

하드웨어 리소스의 상태를 분석하여 문제를 배제해 나가는 방식이다.
각 리소스에서 다음의 세 가지 요소를 확인하여 모니터링을 체계화할 수 있다.

1. **에러 (Error)**: 리소스에 에러가 발생했는지 여부
2. **사용률 (Utilization)**: 리소스의 사용률이 높은지 여부
3. **포화 상태 (Saturation)**: 리소스가 포화 상태로 더 이상 사용할 수 없는지 여부

이 기법을 통해 리소스의 상태를 명확히 분석하고, 문제 원인을 보다 효율적으로 파악할 수 있다.

## 2.1. 에러(**로그)**

```shell
 tail -f /var/log/syslog
```

에러 로그를 남기면 관리자는 지정된 문제 발생 시 로그를 확인하여 
**원인과 해결 방안을 보다 쉽게 파악**할 수 있다. 에러가 발생할 경우 아래와 같은 사항들을 점검한다.

1. **직전 배포와의 연관성**: 최근 배포와 관련이 있는지 확인
2. **특정 사용자, 시간, 조건에 따른 문제인지**: 특정 상황에서만 발생하는지 확인
3. **에러 발생 맥락**: 에러가 발생하는 구체적인 상황을 파악

로그의 종류는 아래와 같다.

- **시스템 로그**: `/var/log/syslog`, `cron`, `dmesg` 등 시스템 관련 로그
- **애플리케이션 로그**: 어플리케이션 자체에서 남기는 로그

로깅을 할 때 주의해야 할 점은 다음과 같다.

1. **부작용 주의 (Side Effects)**: 로깅이 앱의 기능에 영향을 미치지 않도록 주의
2. **설명 구체화**: 충분한 설명을 위해 로그에 데이터와 상세 설명을 포함
3. **로그 메서드 관리**: 메서드의 input과 output을 로그에 남길 때, 중복 부분은 AOP로 해결
4. **개인정보 보호**: 사용자의 개인정보가 로그에 노출되지 않도록 주의

## 2.2. **사용률**

![image.png](/.asset/kwj1270/week1-25.png)


서버의 사용률을 통해 문제 상황을 감지할 수 있다. 
주로 **CPU 사용률**, **가용 메모리 (Available Memory)**,   
**RX/TX 패킷량**, **디스크 사용률 (Disk Usage)**, **IOPS** 등을 확인한다.

서버 리소스 확인 방법은 아래와 같다. 

1. **스크립트를 통한 리소스 확인**: 
[스크립트](https://github.com/woowacourse/script-practice/blob/master/script/server_resource.sh)를 사용해 서버 리소스를 체크할 수 있다.
2. **Load Average 확인**: 
[Load average](https://brainbackdoor.tistory.com/117)가 높을 경우, 시스템 부하를 유발하는 프로세스를 확인한다.
3. **시스템 메시지 확인**: 
`oom-killer` 등의 시스템 메시지가 발생하면 `dmesg` 또는 `syslog`에서 해당 메시지를 확인한다.
4. **vmstat로 OS 커널 정보 확인**: 
`vmstat` 명령어를 통해 OS 커널에서 제공하는 다양한 상태 정보를 확인한다.

### 2.2.1. vmstat 명령어로 시스템 상태 확인하기

`vmstat` 명령어를 통해 시스템의 메모리, CPU, I/O 상태 등을 모니터링할 수 있다.

```bash
$ vmstat 5 5
procs -----------memory---------- ---swap-- -----io---- -system-- ------cpu-----
 r  b   swpd   free   buff  cache   si   so    bi    bo   in   cs us sy id wa st
 0  0      0 20774188 124872 10659336    0    0     0     9    3    3  0  0 100  0  0
 0  0      0 20771628 124872 10659576    0    0     0     0 1143 1041  0  0 99  0  0
```

- **si, so**: **swap-in**과 **swap-out** 값. 값이 0이 아닐 경우, 시스템 메모리가 부족하다는 의미다.
- **us, sy, id, wa, st**:
    - **us**: user time (사용자가 사용하는 CPU 시간)
    - **sy**: system time (커널에서 사용하는 CPU 시간)
    - **id**: idle (유휴 상태)
    - **wa**: wait I/O (I/O 대기 시간)
    - **st**: stolen time (hypervisor가 가상 CPU를 서비스하는 동안 실제 CPU를 차지한 시간)
- I/O 대기와 관련해서는 wa 가 아닌, b 열을 참고한다.

### 2.2.2 iostat 디스크 사용률 확인하기

iostat을 통해 OS 커널에서 취득한 디스크 사용률을 알 수 있다.

```
$ iostat -xt
Linux 5.4.0-1038-aws (ip-192-168-0-146.ap-northeast-2.compute.internal) 	03/19/21 	_x86_64_	(8 CPU)

03/19/21 14:59:35
avg-cpu:  %user   %nice %system %iowait  %steal   %idle
           0.15    0.00    0.08    0.00    0.04   99.73

Device            r/s     rkB/s   rrqm/s  %rrqm r_await rareq-sz     w/s     wkB/s   wrqm/s  %wrqm w_await wareq-sz     d/s     dkB/s   drqm/s  %drqm d_await dareq-sz  aqu-sz  %util
loop0            0.01      0.01     0.00   0.00    0.30     1.04    0.00      0.00     0.00   0.00    0.00     0.00    0.00      0.00     0.00   0.00    0.00     0.00    0.00   0.00
loop1            0.00      0.00     0.00   0.00    2.27     1.25    0.00      0.00     0.00   0.00    0.00     0.00    0.00      0.00     0.00   0.00    0.00     0.00    0.00   0.00

```

### 2.2.3. free 메모리 사용량 확인하기

free 명령어로 메모리 사용량을 확인할 수 있다.

```kotlin
$ free -wh
              total        used        free      shared     buffers       cache   available
Mem:           31Gi       1.3Gi        19Gi       0.0Ki       122Mi        10Gi        29Gi
Swap:            0B          0B          0B
```

- available : swapping 없이 새로운 프로세스에서 할당 가능한 메모리의 예상 크기

**top**

![image.png](/.asset/kwj1270/week1-22.png)

- VIRT : 프로세스가 확보한 가상 메모리 영역의 크기
- RES : 실제 물리 메모리 영역의 크기
    - RES의 크기가 큰 프로세스가 없는지 확인해야 한다.
    Swap이 발생하고 있다는 것은 물리 메모리가 부족하다는 증거

---

## 2.3. **포화도**

![image.png](/.asset/kwj1270/week1-23.png)
![image.png](/.asset/kwj1270/week1-24.png)

사실 CPU 100% 는 장애가 아니라, 시스템 리소스를 낭비 없이 최대한 활용하는 것이다.
하지만 그럼에도 이를 100% 가 되지 않도록 노력하며 이를 파악하고 있다.

```shell
$ vmstat 5 5
procs -----------memory---------- ---swap-- -----io---- -system-- ------cpu-----
 r  b   swpd   free   buff  cache   si   so    bi    bo   in   cs us sy id wa st
 1  0      0 20728432 125420 10700972    0    0     0     9    3    3  0  0 100  0  0
 0  0      0 20726004 125420 10700992    0    0     0     0 1061  971  0  0 99  0  0

```

- r:
    - CPU에서 동작중인 프로세스 수를 의미한다.
    - CPU 자원이 포화상태인지 파악할 때 활용한다.
    - r 값이 CPU 값보다 클 경우엔 처리를 하지 못해 대기하는 프로세스가 발생한다.

```shell
$ free -wh
              total        used        free      shared     buffers       cache   available
Mem:           31Gi       1.3Gi        19Gi       0.0Ki       122Mi        10Gi        29Gi
Swap:            0B          0B          0B
```

- buffers:
    - Block I/O의 buffer 캐시 사용량을 의미한다.
- cache:
    - 파일시스템에서 사용되는 page cache량을 의미한다.
    - 따라서 이 값이 0일 경우 Disk I/O가 높다는 것을 의미하므로 원인을 파악해보아야 한다.

```shell
$ iostat -xt
Linux 5.4.0-1038-aws (ip-192-168-0-146.ap-northeast-2.compute.internal) 	03/19/21 	_x86_64_	(8 CPU)

03/19/21 15:38:32
avg-cpu:  %user   %nice %system %iowait  %steal   %idle
           0.15    0.00    0.08    0.00    0.04   99.73

Device            r/s     rkB/s   rrqm/s  %rrqm r_await rareq-sz     w/s     wkB/s   wrqm/s  %wrqm w_await wareq-sz     d/s     dkB/s   drqm/s  %drqm d_await dareq-sz  aqu-sz  %util
loop0            0.01      0.01     0.00   0.00    0.30     1.04    0.00      0.00     0.00   0.00    0.00     0.00    0.00      0.00     0.00   0.00    0.00     0.00    0.00   0.00
loop1            0.00      0.00     0.00   0.00    2.27     1.25    0.00      0.00     0.00   0.00    0.00     0.00    0.00      0.00     0.00   0.00    0.00     0.00    0.00   0.00 

```

- await :
    - I/O 처리 평균시간을 의미하며, Application이 이 시간동안 대기하게 된다.
    - 보통 하드웨어 상에 문제가 있거나 디스크를 모두 사용하고 있을 경우에 이슈가 발생한다.

---

# 3. 주로 보는 모니터링 지표

| 대상 | 지표 |
| --- | --- |
| Error | - error count by host<br>- not success request seconds by url |
| Requests | - request seconds (최대 응답시간, 평균 응답시간)<br>- request count by host<br>- request count by url |
| 사용률 & 포화 | - CPU Utilisation by host<br>- CPU Satuartion (load per cpu) by host<br>- Memory Utilisation by host<br>- Memory Satuartion (Mager Page Fault) by host<br>- Memory Swap/Failure<br>- Net Utilisation by host (Bytes Receive/Transmit)<br>- Net Satuartion by host (Drop Receive/Transmit)<br>- Disk IO Utilisation by host<br>- Disk IO Satuartion<br>- heap usage by host |
| WAS 상태 | - JVM live threads by host<br>- JVM blocked threads by host<br>- Tomat busy threads by host<br>- JVM CPU usage by host<br>- GC Memory |
| DB 상태 | - HikariCP Acquire seconds<br>- HikariCP Creation seconds<br>- HikariCP DB Connection pool |
| Redis 상태 | - Cache Misses per Cache Items |