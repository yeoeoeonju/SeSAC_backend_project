# API 명세서

## 목차
1. [개요](#개요)
2. [사용자 API](#사용자-api)
3. [가맹점 API](#가맹점-api)
4. [관리자 API](#관리자-api)
5. [공통 응답 형식](#공통-응답-형식)
6. [오류 코드](#오류-코드)

## 개요
이 문서는 시스템의 REST API 명세를 정의합니다. 모든 API 요청은 다음 기본 URL에서 시작합니다: `https://api.example.com`

### 인증
- 대부분의 API 엔드포인트는 인증이 필요합니다
- 인증은 요청 헤더의 `Authorization` 필드에 JWT 토큰을 포함하여 수행됩니다
- 토큰 형식: `Bearer {token}`

## 사용자 API
기본 경로: `/api/user`

### 인증 관련

| 기능 | 메소드 | 엔드포인트 | 요청 파라미터 | 인증 필요 | 설명 |
|------|--------|------------|--------------|-----------|------|
| 로그인 | POST | `/login` | `{ "email": "string", "password": "string" }` | 아니오 | 사용자 로그인 및 토큰 발급 |
| 로그아웃 | POST | `/logout` | - | 예 | 현재 세션 종료 |
| 비밀번호 변경 | PUT | `/password` | `{ "currentPassword": "string", "newPassword": "string" }` | 예 | 사용자 비밀번호 변경 |

### 사용자 정보

| 기능 | 메소드 | 엔드포인트 | 요청 파라미터 | 인증 필요 | 설명 |
|------|--------|------------|--------------|-----------|------|
| 개인정보 조회 | GET | `/info` | - | 예 | 현재 로그인한 사용자 정보 조회 |
| 포인트 조회 | GET | `/info/points` | - | 예 | 사용자 포인트 정보 조회 |

### 결제 관련

| 기능 | 메소드 | 엔드포인트 | 요청 파라미터 | 인증 필요 | 설명 |
|------|--------|------------|--------------|-----------|------|
| 결제내역 조회 | GET | `/transactions` | `startDate`, `endDate`, `page`, `size` | 예 | 사용자 결제 내역 조회 (페이지네이션 지원) |
| 결제 진행 | POST | `/transactions/{franchiseId}` | `{ "amount": number, "items": [{ "itemId": string, "quantity": number }] }` | 예 | 가맹점에서 결제 진행 |

### 가맹점 조회

| 기능 | 메소드 | 엔드포인트 | 요청 파라미터 | 인증 필요 | 설명 |
|------|--------|------------|--------------|-----------|------|
| 가맹점 목록 조회 | GET | `/franchises` | `category`, `keyword`, `page`, `size` | 예 | 가맹점 목록 조회 (필터링 및 페이지네이션) |
| 가맹점 메뉴 조회 | GET | `/franchises/{franchiseId}/menu` | - | 예 | 특정 가맹점의 메뉴 정보 및 사진 조회 |
| 가맹점 지도 조회 | GET | `/franchises/map` | `latitude`, `longitude`, `radius` | 예 | 특정 위치 기준 반경 내 가맹점 조회 |

## 가맹점 API
기본 경로: `/api/franchise`

### 인증 관련

| 기능 | 메소드 | 엔드포인트 | 요청 파라미터 | 인증 필요 | 설명 |
|------|--------|------------|--------------|-----------|------|
| 로그인 | POST | `/login` | `{ "email": "string", "password": "string" }` | 아니오 | 가맹점 계정 로그인 |
| 로그아웃 | POST | `/logout` | - | 예 | 현재 세션 종료 |
| 비밀번호 변경 | PUT | `/password` | `{ "currentPassword": "string", "newPassword": "string" }` | 예 | 비밀번호 변경 |

### 가맹점 정보

| 기능 | 메소드 | 엔드포인트 | 요청 파라미터 | 인증 필요 | 설명 |
|------|--------|------------|--------------|-----------|------|
| 정보 조회 | GET | `/info` | - | 예 | 가맹점 정보 조회 |
| 정보 수정 | PUT | `/info` | `{ "name": "string", "address": "string", "phoneNumber": "string", ... }` | 예 | 가맹점 정보 수정 |

### 메뉴 관리

| 기능 | 메소드 | 엔드포인트 | 요청 파라미터 | 인증 필요 | 설명 |
|------|--------|------------|--------------|-----------|------|
| 메뉴 사진 등록 | PUT | `/menu` | `FormData` (multipart/form-data) | 예 | 메뉴 사진 및 정보 등록/수정 |
| 메뉴 사진 조회 | GET | `/menu/{fileName}` | - | 예 | 특정 메뉴 사진 조회 |

### 결제 관리

| 기능 | 메소드 | 엔드포인트 | 요청 파라미터 | 인증 필요 | 설명 |
|------|--------|------------|--------------|-----------|------|
| 결제내역 조회 | GET | `/transactions` | `startDate`, `endDate`, `period`, `page`, `size` | 예 | 결제내역 조회 (기간별, 페이지네이션) |
| 결제 취소 | PUT | `/transactions/{transactionId}/cancel` | `{ "reason": "string" }` | 예 | 특정 결제 취소 처리 |
| 정산 기록 조회 | GET | `/settlements` | `startDate`, `endDate`, `period`, `page`, `size` | 예 | 완료된 정산 내역 조회 |

## 관리자 API
기본 경로: `/api/customer`

### 인증 관련

| 기능 | 메소드 | 엔드포인트 | 요청 파라미터 | 인증 필요 | 설명 |
|------|--------|------------|--------------|-----------|------|
| 로그인 | POST | `/login` | `{ "email": "string", "password": "string" }` | 아니오 | 관리자 로그인 |
| 로그아웃 | POST | `/logout` | - | 아니오 | 세션 종료 |

### 사용자 관리

| 기능 | 메소드 | 엔드포인트 | 요청 파라미터 | 인증 필요 | 설명 |
|------|--------|------------|--------------|-----------|------|
| 사용자 등록 | POST | `/users` | `{ "name": "string", "email": "string", ... }` | 아니오 | 새 사용자 등록 |
| 사용자 목록 조회 | GET | `/users` | `category`, `keyword`, `page`, `size` | 아니오 | 사용자 목록 조회 (필터링, 페이지네이션) |
| 사용자 상세 조회 | GET | `/users/{userId}` | - | 아니오 | 특정 사용자 상세 정보 조회 |
| 사용자 수정 | PUT | `/users/{userId}` | `{ "name": "string", "email": "string", ... }` | 아니오 | 사용자 정보 수정 |
| 사용자 비활성화 | PUT | `/users/{userId}/deactivate` | - | 아니오 | 사용자 계정 비활성화 |
| 비활성 사용자 조회 | GET | `/users/inactive` | `page`, `size` | 아니오 | 비활성화된 사용자 목록 조회 |
| 사용자 활성화 | PUT | `/users/{userId}/activate` | - | 아니오 | 비활성 사용자 계정 활성화 |

### 포인트 관리

| 기능 | 메소드 | 엔드포인트 | 요청 파라미터 | 인증 필요 | 설명 |
|------|--------|------------|--------------|-----------|------|
| 포인트 지급 | POST | `/points` | `{ "userIds": [number], "point": number, "reason": "string" }` | 아니오 | 다수 사용자에게 포인트 지급 |
| 포인트 수정 | PUT | `/points/{userId}` | `{ "point": number, "reason": "string" }` | 아니오 | 특정 사용자 포인트 수정 |

### 그룹 관리

| 기능 | 메소드 | 엔드포인트 | 요청 파라미터 | 인증 필요 | 설명 |
|------|--------|------------|--------------|-----------|------|
| 그룹 등록 | POST | `/groups` | `{ "name": "string", "policy": {}, ... }` | 아니오 | 새 그룹 등록 (정책 포함) |
| 그룹 목록 조회 | GET | `/groups` | `category`, `keyword`, `page`, `size` | 아니오 | 활성화된 그룹 목록 조회 |
| 그룹 상세 조회 | GET | `/groups/{partyId}` | - | 아니오 | 특정 그룹 상세 정보 조회 |
| 그룹 수정 | PUT | `/groups/{partyId}` | `{ "name": "string", "policy": {}, ... }` | 아니오 | 그룹 정보 및 정책 수정 |
| 그룹 비활성화 | PUT | `/groups/{partyId}/deactivate` | - | 아니오 | 그룹 비활성화 |

### 가맹점 관리

| 기능 | 메소드 | 엔드포인트 | 요청 파라미터 | 인증 필요 | 설명 |
|------|--------|------------|--------------|-----------|------|
| 가맹점 등록 | POST | `/franchises` | `{ "name": "string", "address": "string", ... }` | 아니오 | 새 가맹점 등록 |
| 가맹점 목록 조회 | GET | `/franchises` | `category`, `keyword`, `page`, `size` | 아니오 | 활성화된 가맹점 목록 조회 |
| 가맹점 상세 조회 | GET | `/franchises/{franchiseId}` | - | 아니오 | 특정 가맹점 상세 정보 조회 |
| 가맹점 수정 | PUT | `/franchises/{franchiseId}` | `{ "name": "string", "address": "string", ... }` | 아니오 | 가맹점 정보 수정 |
| 가맹점 비활성화 | PUT | `/franchises/{franchiseId}/deactivate` | - | 아니오 | 가맹점 비활성화 |
| 비활성 가맹점 조회 | GET | `/franchises/inactive` | `page`, `size` | 아니오 | 비활성화된 가맹점 목록 조회 |
| 가맹점 활성화 | PUT | `/franchises/{franchiseId}/activate` | - | 아니오 | 비활성 가맹점 활성화 |

### 거래 관리

| 기능 | 메소드 | 엔드포인트 | 요청 파라미터 | 인증 필요 | 설명 |
|------|--------|------------|--------------|-----------|------|
| 가맹점 기준 거래 내역 | GET | `/transactions/franchises` | `category`, `keyword`, `startDate`, `endDate`, `page`, `size` | 아니오 | 가맹점별 거래 내역 조회 |
| 그룹 기준 거래 내역 | GET | `/transactions/groups` | `category`, `keyword`, `startDate`, `endDate`, `page`, `size` | 아니오 | 그룹별 거래 내역 조회 |
| 사용자 기준 거래 내역 | GET | `/transactions/users` | `category`, `keyword`, `startDate`, `endDate`, `page`, `size` | 아니오 | 사용자별 거래 내역 조회 |

### 정산 관리

| 기능 | 메소드 | 엔드포인트 | 요청 파라미터 | 인증 필요 | 설명 |
|------|--------|------------|--------------|-----------|------|
| 미정산 내역 조회 | GET | `/settlements/pending` | `year`, `month`, `page`, `size` | 아니오 | 정산이 필요한 내역 조회 |
| 정산 처리 | PUT | `/settlements/{restaurantId}` | `{ "year": number, "month": number }` | 아니오 | 특정 가맹점 정산 처리 |
| 정산 내역 조회 | GET | `/settlements/history` | `year`, `month`, `franchiseId`, `page`, `size` | 아니오 | 정산 완료된 