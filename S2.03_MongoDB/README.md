# S2.03_MongoDB — Document Database Design

![MongoDB](https://img.shields.io/badge/MongoDB-5-47A248?logo=mongodb)

## Description

Document-oriented database design for four business domains using MongoDB. Each level models the same domains as the MySQL schemas but adapted for a NoSQL approach: embedded documents, array fields, and `$lookup`-based views. Collections use `$jsonSchema` validators to enforce document structure.

## What I Learned

- Designing MongoDB schemas with embedded documents vs. references
- `$jsonSchema` validation for collections
- `$lookup` aggregation to create relational-like views across collections
- `$unwind`, `$group`, `$facet` for aggregation pipelines
- Using `$out` to persist aggregation results into new collections
- Auto-increment-like counters with `findAndModify` (Spotify)
- Choosing between embedded arrays (pizzeria employees inside stores) and separate collections

## Files

| Level | Domain | Init Script | Views Script |
|-------|--------|-------------|-------------|
| N1 | Optician | [N1-Optica/Scripts/initOptica.js](N1-Optica/Scripts/initOptica.js) | [createViewsOptica.js](N1-Optica/Scripts/createViewsOptica.js) |
| N1 | Pizzeria | [N1-Pizzeria/Scripts/initPizzeria.js](N1-Pizzeria/Scripts/initPizzeria.js) | [createViewsPizzeria.js](N1-Pizzeria/Scripts/createViewsPizzeria.js) |
| N2 | YouTube | [N2-YouTube/Scripts/initYouTube.js](N2-YouTube/Scripts/initYouTube.js) | [createViewsYoutube.js](N2-YouTube/Scripts/createViewsYoutube.js) |
| N3 | Spotify | [N3-Spotify/initSpotify.js](N3-Spotify/initSpotify.js) | — |

### Collection Models

- **Optica** (`optica`) — `suppliers` (with embedded address, brands array), `glasses` (graduation, frame as subdocuments, optional `client_id` reference), `clients` (address, `recommendedBy` self-reference). Views join clients→glasses, glasses→suppliers, suppliers→glasses via `$lookup`.
- **Pizzeria** (`pizzeria`) — `provincias` (embedded localidades), `clientes` (embedded provincia/localidad, pedidos array), `productos` (pizza/hamburguesa/bebida, optional categoria), `pedidos` (delivery/pickup, embedded delivery info), `tiendas` (embedded empleados array). Views: `tickets` (pedidos+productos), `ventasTiendas` (tiendas+pedidos).
- **YouTube** (`youtube`) — `usuarios` (embedded canales with suscriptores, playlists), `videos` (embedded likes/dislikes, tags, comentarios with nested likes/dislikes). Aggregation: `videos_likes_dislikes` via `$facet`+`$out`.
- **Spotify** (`spotify`) — `counters` (auto-increment), `users` (embedded subscription with payment_mode as paypal or credit card), `playlists`, `albums` (embedded songs), `artists` (embedded relatedArtists, albums refs).

## How to Run

```bash
mongosh < N1-Optica/Scripts/initOptica.js
mongosh < N1-Optica/Scripts/createViewsOptica.js
mongosh < N1-Pizzeria/Scripts/initPizzeria.js
mongosh < N1-Pizzeria/Scripts/createViewsPizzeria.js
mongosh < N2-YouTube/Scripts/initYouTube.js
mongosh < N2-YouTube/Scripts/createViewsYoutube.js
mongosh < N3-Spotify/initSpotify.js
```

## Built With

- MongoDB 5+
- mongosh (MongoDB Shell)
