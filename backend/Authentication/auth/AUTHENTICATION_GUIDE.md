# Ghid de Autentificare JWT

Acest ghid explică cum să folosești sistemul de autentificare bazat pe token-uri JWT implementat în aplicația Spring Boot.

## Structura Sistemului

### Componente Principale

1. **AuthController** - Endpoint-uri pentru înregistrare și autentificare
2. **JwtService** - Serviciu pentru generarea și validarea token-urilor JWT
3. **JwtAuthenticationFilter** - Filtru pentru validarea automată a token-urilor
4. **SecurityConfiguration** - Configurația de securitate Spring Security
5. **ProtectedController** - Endpoint-uri protejate pentru testare
6. **TokenController** - Endpoint-uri pentru validarea token-urilor

## Endpoint-uri Disponibile

### 1. Înregistrare Utilizator
```
POST /auth/register
Content-Type: application/json

{
    "username": "testuser",
    "password": "password123"
}
```

**Răspuns:**
```json
{
    "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
    "authUserDTO": {
        "id": "uuid-here",
        "username": "testuser"
    }
}
```

### 2. Autentificare (Login)
```
POST /auth/login
Content-Type: application/json

{
    "username": "testuser",
    "password": "password123"
}
```

**Răspuns:**
```json
{
    "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
    "authUserDTO": {
        "id": "uuid-here",
        "username": "testuser"
    }
}
```

### 3. Endpoint-uri Protejate

#### Informații utilizator
```
GET /api/protected/user-info
Authorization: Bearer <token>
```

#### Test autentificare
```
GET /api/protected/test
Authorization: Bearer <token>
```

#### Endpoint doar pentru admini
```
GET /api/protected/admin-only
Authorization: Bearer <token>
```

#### Endpoint doar pentru clienți
```
GET /api/protected/client-only
Authorization: Bearer <token>
```

### 4. Validare Token

#### Validează token
```
POST /api/token/validate
Content-Type: application/json

{
    "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}
```

#### Informații din token
```
POST /api/token/info
Content-Type: application/json

{
    "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}
```

## Cum să Folosești Token-ul

### 1. Obține Token-ul
Fă o cerere POST la `/auth/login` cu username și password pentru a obține token-ul.

### 2. Folosește Token-ul în Cereri
Adaugă header-ul `Authorization` cu valoarea `Bearer <token>` în toate cererile către endpoint-urile protejate.

**Exemplu cu curl:**
```bash
curl -X GET http://localhost:8082/api/protected/user-info \
  -H "Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
```

**Exemplu cu JavaScript:**
```javascript
fetch('/api/protected/user-info', {
    headers: {
        'Authorization': 'Bearer ' + token
    }
})
```

## Configurație

### Variabile de Mediu
```bash
DB_IP=localhost
DB_PORT=5433
DB_USER=postgres
DB_PASSWORD=roxanna
DB_DBNAME=credential_database
PORT=8082
```

### Configurație Baza de Date
Aplicația folosește PostgreSQL și va crea automat tabela `credentials` la prima rulare.

## Securitate

### Caracteristici de Securitate
- Parolele sunt hash-uite cu BCrypt
- Token-urile JWT au o durată de viață de 24 de ore
- CSRF este dezactivat pentru API-uri
- CORS este activat cu setările implicite
- Sesțiunile sunt stateless

### Roluri
- **CLIENT** - Rolul implicit pentru utilizatori noi
- **ADMIN** - Rol pentru administratori (trebuie setat manual în baza de date)

## Testare

### 1. Testează Înregistrarea
```bash
curl -X POST http://localhost:8082/auth/register \
  -H "Content-Type: application/json" \
  -d '{"username": "testuser", "password": "password123"}'
```

### 2. Testează Autentificarea
```bash
curl -X POST http://localhost:8082/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username": "testuser", "password": "password123"}'
```

### 3. Testează Endpoint Protejat
```bash
curl -X GET http://localhost:8082/api/protected/test \
  -H "Authorization: Bearer <token-ul-obtinut>"
```

## Gestionarea Erorilor

### Erori Comune
- **401 Unauthorized** - Token invalid sau expirat
- **403 Forbidden** - Nu ai permisiunea necesară
- **400 Bad Request** - Date invalide în cerere

### Mesaje de Eroare
Aplicația returnează mesaje de eroare descriptive în format JSON pentru a facilita debugging-ul.

## Dezvoltare

### Adăugarea de Endpoint-uri Protejate
Pentru a adăuga un nou endpoint protejat:

1. Creează metoda în controller
2. Adaugă `@PreAuthorize` dacă vrei să limitezi accesul pe baza rolurilor
3. Folosește `SecurityContextHolder.getContext().getAuthentication()` pentru a obține informații despre utilizatorul autentificat

**Exemplu:**
```java
@GetMapping("/my-endpoint")
@PreAuthorize("hasRole('CLIENT')")
public ResponseEntity<String> myEndpoint() {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    String username = auth.getName();
    return ResponseEntity.ok("Hello " + username);
}
```

