```bash
export JDBC_DATABASE_URL=jdbc:postgresql://<host>:<port>/<dbname>?sslmode=require&user=<username>&password=<password>

heroku login
heroku create
heroku git:remote -a bakigoal-soccer-stats-api
heroku pg:info
heroku pg:psql

git push heroku master
```
---
https://bakigoal-soccer-api.herokuapp.com

- /api/v1/leagues/{leagueId}
- /api/v1/leagues/{leagueId}/{year}/standings
- /api/v1/leagues/{leagueId}/{year}/topscorers
- /api/v1/leagues/{leagueId}/{year}/topassists
- /api/v1/teams/{teamId}/squad

---
