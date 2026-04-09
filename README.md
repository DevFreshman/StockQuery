# StockQuery

`StockQuery` là REST API phân tích dữ liệu chứng khoán Việt Nam, xây bằng Spring Boot + PostgreSQL.  
Project hỗ trợ nhập mã cổ phiếu, nhập lịch sử giá và truy vấn các chỉ số phân tích như **top tăng/giảm**, **hiệu suất theo ngành**, **volume spike**.

## Công nghệ sử dụng

- Java 21
- Spring Boot 4.0.5 (Web MVC, Data JPA)
- PostgreSQL 16
- SpringDoc OpenAPI (Swagger UI)
- Lombok

## Kiến trúc chính

- `controller`: định nghĩa REST API (`/vn-stocks/**`)
- `service`: nghiệp vụ
- `repository`: tách lớp query/command:
  - `impl`: triển khai repository
  - `jpa/query`: JPA + native SQL cho các bài toán phân tích
  - `jpa/command`: thao tác ghi dữ liệu
  - `specification`: lọc động theo nhiều điều kiện
- `dto`: request/response model
- `repository/entity`: ánh xạ bảng dữ liệu

## Dữ liệu và schema

Project dùng 2 bảng chính:

- `companies`
- `hs_stock_prices`

File `init.sql` chứa schema + dữ liệu mẫu (khoảng 5 mã cổ phiếu, hơn 5000 bản ghi lịch sử giá).

## Chạy project

### 1. Khởi động PostgreSQL

```powershell
docker compose up -d
```

Mặc định:

- DB: `vnstocks`
- User: `admin`
- Password: `secret`
- Port: `5432`

### 2. Import schema + dữ liệu mẫu

```powershell
Get-Content .\init.sql | docker exec -i pg_dev psql -U admin -d vnstocks
```

### 3. Chạy ứng dụng

```powershell
.\mvnw spring-boot:run
```

### 4. Truy cập API docs

- Swagger UI: `http://localhost:8080/swagger-ui.html`
- OpenAPI JSON: `http://localhost:8080/api-docs`

## Danh sách API

Base path: `http://localhost:8080/vn-stocks`

| Method | Path | Mô tả |
|---|---|---|
| GET | `/stock-price` | Lấy lịch sử giá theo filter |
| GET | `/companies` | Lấy danh sách công ty theo filter |
| GET | `/performance/sector` | Thống kê hiệu suất theo ngành |
| GET | `/top/gainer` | Danh sách mã tăng mạnh nhất (theo % thay đổi gần nhất) |
| GET | `/top/loser` | Danh sách mã giảm mạnh nhất |
| GET | `/volume/spikes` | Phát hiện volume spike theo ngày |
| POST | `/import/ticker` | Import mã cổ phiếu mới |
| POST | `/import/ticker/{ticker}/history` | Import dữ liệu giá cho 1 mã |

## Chi tiết filter query

### GET `/stock-price`

Phân trang:

- `page` (mặc định `0`)
- `size` (mặc định `10`)

Filter:

- `ticker`
- `tradeDateFrom`, `tradeDateTo` (định dạng `yyyy-MM-dd`)
- `openPriceGreaterThan`, `openPriceLessThan`
- `highestPriceGreaterThan`, `highestPriceLessThan`
- `lowestPriceGreaterThan`, `lowestPriceLessThan`
- `closePriceGreaterThan`, `closePriceLessThan`
- `volumeGreaterThan`, `volumeLessThan`

### GET `/companies`

Phân trang:

- `page` (mặc định `0`)
- `size` (mặc định `10`)

Filter:

- `ticker`
- `companyName`
- `exchange`
- `sector`
- `industry`

### GET `/volume/spikes`

Phân trang:

- `page` (mặc định `0`)
- `size` (mặc định `10`)

Filter:

- `date` (LocalDate, định dạng `yyyy-MM-dd`)
- `baselineDays` (số ngày nền để tính trung bình volume)
- `spikeThreshold` (ngưỡng spike, ví dụ `1.5`)

## Ví dụ request

### Import ticker

```http
POST /vn-stocks/import/ticker
Content-Type: application/json

{
  "ticker": "ABC",
  "companyName": "ABC Corp",
  "exchange": "HOSE",
  "sector": "Technology",
  "industry": "Software"
}
```

### Import lịch sử giá

```http
POST /vn-stocks/import/ticker/ABC/history
Content-Type: application/json

{
  "ticker": "ABC",
  "tradeDate": "2026-04-01",
  "openPrice": 10000,
  "highestPrice": 10500,
  "lowestPrice": 9900,
  "closePrice": 10300,
  "volume": 1500000
}
```

### Query top gainer

```http
GET /vn-stocks/top/gainer?page=0&size=10
```

## Response format phân trang

Các API dạng danh sách trả về:

```json
{
  "content": [],
  "page": 0,
  "size": 10,
  "totalElements": 0,
  "totalPages": 0
}
```

## Lưu ý quan trọng

- `spring.jpa.hibernate.ddl-auto=validate`: ứng dụng yêu cầu schema DB phải tồn tại và đúng với entity trước khi chạy.
- `init.sql` hiện chưa có cột `sector` trong bảng `companies`, trong khi entity/query trong mã nguồn có dùng trường này. Nếu chạy theo mã nguồn hiện tại, cần bổ sung cột `sector` vào schema.
