# TikTokLiveBridge

---

## Overview

TikTokLiveBridge is a bridge plugin that connects TikTok LIVE events to Minecraft server chat in real time.  
It allows streamers to directly link their TikTok live audience with in-game players.

Japanese version: [README_JP.md](./README_JP.md)

---

## Features
- Chat comment display
- Follow notifications
- Join notifications
- Gift notifications

---

## Installation
1. Place the plugin into the `plugins` folder
2. Start the server (config.yml will be generated)
3. Edit `config.yml`
4. Restart the server

---

## Configuration Example

Full config:
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
  follow: "%user% followed the stream!"
  join: "%user% joined the stream!"
  gift: "%user% sent %gift%!"
```

### tiktok
Set your TikTok username (without @)

```yaml
tiktok:
  username: "your_tiktok_username"
```

### features
Enable or disable event notifications

```yaml
features:
  comment: true
  follow: true
  join: true
  gift: true
```

### messages
Customize displayed messages for each event

```yaml
features:
  comment: "%user%: %message%"
  follow: "%user% がフォローしました！"
  join: "%user% が参加しました！"
  gift: "%user% が %gift% を送りました！"
```
---

## Notes
- The plugin only works while TikTok LIVE is active 
- If the username is not set, the plugin will disable itself 
- TikTok API changes may break functionality in the future
---

## Dependencies
TikTokLiveJava
https://github.com/jwdeveloper/TikTokLiveJava