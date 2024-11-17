# 후보 1
응답 response의 setter를 열어두고 각 object를 가져와 필드들을 한 곳에서 set해요.
현재 구조의 문제점은 각 도메인이 뒤엉켜 응답 object를 만드는 거예요. 이를 개선하면 좋겠어요.
각 도메인별로 응답을 만드는 걸 분리하는 게 나을 것 같아요.
# 후보 2
응답 response의 setter를 열어두고 각 object를 가져올 때마다 각 필드를 set해요. object마다 loop를 돌려야 해요.
상품 루프 돌고, 환불 루프 돌고, 배송 루프를 돌면서 응답값에 set하는 형태예요. API 하나로 모든 걸 해결하는 방식이에요.
# 후보 3
응답 response의 setter를 막아버리고, 각 object를 가져와 생성자 파라미터로 넘겨서 응답을 만들어요.
JSON 응답에 대한 필드를 한곳에서 set/설정하게 돼요.
각 object에 대한 필드 설정이 40줄 정도 한 곳에 모일 수 있어요.
각 도메인별로 응답을 분리하지 않고, 한 곳에서 요약하는 형태예요.
주문 상세처럼 복잡한 API에서는 NoSQL(몽고DB)을 사용해 응답을 한 번에 관리하는 방법도 고려할 수 있어요.
참고) 대규모 트랜잭션을 처리하는 배민 주문시스템 24:13 ~ 25:30 (2023 우아콘) -> 해당 영상도 도움이 됐어요!
# 후보 4
응답 response를 각 object로 나눠서 프론트에서 여러 번 호출하게 해요.
캐시를 하면 성능이 더 좋아져서 프론트에 요구할 수 있어요.
백엔드에서 상품팀, 주문팀, 환불팀, 배송팀이 분리돼 있으면 프론트에서 따로 호출하는 그림이 그려질 거예요.
추후 확장성이나 관리 주체를 생각하면 API가 아예 분리되는 게 더 좋을 수 있겠어요.

Architecture