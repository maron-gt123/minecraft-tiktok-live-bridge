# TikTokLiveBridge

---

## 概要

TikTok LIVE のイベントを Minecraft サーバー内チャットへリアルタイムで表示するブリッジプラグインです。
配信とゲーム内コミュニティを直接つなぐことができます。
English version: [README.md](./README.md)
---
## 対応機能
- コメント表示
- フォロー通知
- 参加通知
- ギフト通知
---

## 導入方法
1. プラグインを `plugins` フォルダに配置
2. サーバーを起動（config.yml生成）
3. `config.yml` を編集
4. サーバーを再起動
---

## 設定例
config全文

```yaml
tiktok:
  username: "your_tiktok_username"

features:
  comment: true
  follow: true
  join: true
  gift: true

messages:
  comment: "%user%: %message%"
  follow: "%user% がフォローしました！"
  join: "%user% が参加しました！"
  gift: "%user% が %gift% を送りました！"
```

### tiktok
tiktok user名を設定　※ @ 不要

```yaml
tiktok:
  username: "your_tiktok_username"
```

### features
メッセージ通知の要否を`true/false`で設定

```yaml
features:
  comment: true
  follow: true
  join: true
  gift: true
```

### messages
各メッセージ内容を設定

```yaml
features:
  comment: "%user%: %message%"
  follow: "%user% がフォローしました！"
  join: "%user% が参加しました！"
  gift: "%user% が %gift% を送りました！"
```
---

## 注意事項
- TikTok LIVE中でないと接続できません
- username 未設定の場合はプラグインが無効化されます
- TikTok側の仕様変更で動作しなくなる可能性があります
---

## 使用ライブラリ
TikTokLiveJava
https://github.com/jwdeveloper/TikTokLiveJava