# M5_MySQL — Database Schema Design

![MySQL](https://img.shields.io/badge/MySQL-8-4479A1?logo=mysql)

## Description

Database schema design for four real-world business domains using MySQL. Each level models a different domain with properly normalized tables, foreign key relationships, and constraints. Includes MySQL Workbench `.mwb` models and `.png` diagrams.

## What I Learned

- Designing relational schemas with 1:N and M:N relationships
- Using `ENUM`, `DECIMAL`, `FLOAT`, and temporal column types
- Defining composite primary keys and multi-column foreign keys
- Implementing self-referencing foreign keys (optician client referrals)
- Modeling inheritance-like structures (payment methods in Spotify — credit card / PayPal subtypes)
- Database forward engineering with MySQL Workbench

## Files

| Level | Domain | File |
|-------|--------|------|
| N1 | Optician Shop | [N1/Óptica/Optica_Script.sql](N1/%C3%93ptica/Optica_Script.sql) |
| N1 | Pizzeria | [N1/Pizzería/Pizzeria_Script.sql](N1/Pizzer%C3%ADa/Pizzeria_Script.sql) |
| N2 | YouTube | [N2/YouTube_Script.sql](N2/YouTube_Script.sql) |
| N3 | Spotify | [N3/Spotify_Script.sql](N3/Spotify_Script.sql) |

### Schema Overview

- **Optica** (`Óptica`) — Suppliers, brands, glasses (frame type/color, graduation, lens color), clients (with referral tracking), employees, and sales tickets.
- **Pizzeria** — Provinces, localities, clients, stores, employees (cook/delivery), products (pizza/hamburger/drink with pizza categories), orders (pickup or delivery), delivery tracking, and order-product line items.
- **YouTube** — Users, videos (visibility states), channels, subscribers, comments, likes/dislikes (on videos and comments), tags, and playlists.
- **Spotify** — Users (free/premium), payment methods (credit card / PayPal), subscriptions, payment orders, artists, related artists, albums, songs, playlists (active/deleted/shared), playlist songs, and favourite songs.

## How to Run

```bash
mysql -u root -p < "N1/Óptica/Optica_Script.sql"
mysql -u root -p < "N1/Pizzería/Pizzeria_Script.sql"
mysql -u root -p < N2/YouTube_Script.sql
mysql -u root -p < N3/Spotify_Script.sql
```

## Built With

- MySQL 8
- MySQL Workbench
