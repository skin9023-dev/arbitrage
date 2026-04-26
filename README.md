# arbitrage-monitor (Java 17 / Spring Boot)

一个可直接启动的最小工程，用于你当前策略里的 **Funding + Basis 双阈值开仓监控**：

- Binance 合约做空
- Gate / Bitget 现货做多
- 开仓必须同时满足：
    1) 资金费率净收益达到阈值
    2) 差价净优势（基差）达到阈值
    3) 持续时间达到阈值
- 平仓支持“价差回归自动平”：
    - `netBasisPercent <= basisCloseThresholdPercent`

## 运行

```bash
mvn spring-boot:run
```

## 测试

```bash
mvn test
```

## API

### `POST /api/signals/funding-basis/open`

请求示例：

```json
{
  "perpSellPrice": 101.0,
  "spotBuyPrice": 100.0,
  "expectedFundingPercent": 0.35,
  "totalCostPercent": 0.10,
  "confirmSecondsObserved": 10
}
```

返回示例：

```json
{
  "open": true,
  "netFundingPercent": 0.250000,
  "grossBasisPercent": 1.000000,
  "netBasisPercent": 0.900000,
  "reason": "fundingPass=true, basisPass=true, confirmPass=true"
}
```

### `POST /api/signals/funding-basis/close`

请求示例：

```json
{
  "perpBuyPrice": 100.02,
  "spotSellPrice": 100.0,
  "totalCostPercent": 0.02
}
```

返回示例：

```json
{
  "close": true,
  "grossBasisPercent": 0.020000,
  "netBasisPercent": 0.000000,
  "reason": "closePass=true, netBasisPercent=0.000000, closeThresholdPercent=0.03"
}
```

## 配置

`application.yml`：

```yaml
strategy:
  funding-threshold-percent: 0.08
  basis-threshold-percent: 0.20
  basis-close-threshold-percent: 0.03
  confirm-seconds: 8
```

## 下一步建议

- 接入 Binance/Gate/Bitget 实时行情（WebSocket）
- 加入成交深度模拟（可实现价差）
- 加入仓位状态机（OPENING/HEDGED/CLOSING）
- 再连接自动下单模块（Execution）
