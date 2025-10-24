#!/bin/bash

# Script pentru testarea sistemului de autentificare JWT
# Asigură-te că aplicația rulează pe localhost:8082

BASE_URL="http://localhost:8082"
USERNAME="testuser"
PASSWORD="password123"

echo "=== Testare Sistem Autentificare JWT ==="
echo ""

# Test 1: Înregistrare utilizator
echo "1. Testare înregistrare utilizator..."
REGISTER_RESPONSE=$(curl -s -X POST $BASE_URL/auth/register \
  -H "Content-Type: application/json" \
  -d "{\"username\": \"$USERNAME\", \"password\": \"$PASSWORD\"}")

echo "Răspuns înregistrare: $REGISTER_RESPONSE"
echo ""

# Extrage token-ul din răspuns (simplu, pentru demo)
TOKEN=$(echo $REGISTER_RESPONSE | grep -o '"token":"[^"]*"' | cut -d'"' -f4)

if [ -z "$TOKEN" ]; then
    echo "Eroare: Nu s-a putut obține token-ul din răspunsul de înregistrare"
    echo "Încercăm login..."
    
    # Test 2: Login
    echo "2. Testare login..."
    LOGIN_RESPONSE=$(curl -s -X POST $BASE_URL/auth/login \
      -H "Content-Type: application/json" \
      -d "{\"username\": \"$USERNAME\", \"password\": \"$PASSWORD\"}")
    
    echo "Răspuns login: $LOGIN_RESPONSE"
    TOKEN=$(echo $LOGIN_RESPONSE | grep -o '"token":"[^"]*"' | cut -d'"' -f4)
fi

if [ -z "$TOKEN" ]; then
    echo "Eroare: Nu s-a putut obține token-ul. Verifică că aplicația rulează."
    exit 1
fi

echo "Token obținut: ${TOKEN:0:50}..."
echo ""

# Test 3: Validare token
echo "3. Testare validare token..."
VALIDATE_RESPONSE=$(curl -s -X POST $BASE_URL/api/token/validate \
  -H "Content-Type: application/json" \
  -d "{\"token\": \"$TOKEN\"}")

echo "Răspuns validare: $VALIDATE_RESPONSE"
echo ""

# Test 4: Informații din token
echo "4. Testare informații din token..."
INFO_RESPONSE=$(curl -s -X POST $BASE_URL/api/token/info \
  -H "Content-Type: application/json" \
  -d "{\"token\": \"$TOKEN\"}")

echo "Răspuns informații: $INFO_RESPONSE"
echo ""

# Test 5: Endpoint protejat - test autentificare
echo "5. Testare endpoint protejat - test autentificare..."
TEST_RESPONSE=$(curl -s -X GET $BASE_URL/api/protected/test \
  -H "Authorization: Bearer $TOKEN")

echo "Răspuns test: $TEST_RESPONSE"
echo ""

# Test 6: Endpoint protejat - informații utilizator
echo "6. Testare endpoint protejat - informații utilizator..."
USER_INFO_RESPONSE=$(curl -s -X GET $BASE_URL/api/protected/user-info \
  -H "Authorization: Bearer $TOKEN")

echo "Răspuns informații utilizator: $USER_INFO_RESPONSE"
echo ""

# Test 7: Endpoint protejat - doar pentru clienți
echo "7. Testare endpoint protejat - doar pentru clienți..."
CLIENT_RESPONSE=$(curl -s -X GET $BASE_URL/api/protected/client-only \
  -H "Authorization: Bearer $TOKEN")

echo "Răspuns endpoint client: $CLIENT_RESPONSE"
echo ""

# Test 8: Endpoint protejat - doar pentru admini (ar trebui să eșueze)
echo "8. Testare endpoint protejat - doar pentru admini (ar trebui să eșueze)..."
ADMIN_RESPONSE=$(curl -s -X GET $BASE_URL/api/protected/admin-only \
  -H "Authorization: Bearer $TOKEN")

echo "Răspuns endpoint admin: $ADMIN_RESPONSE"
echo ""

# Test 9: Test fără token (ar trebui să eșueze)
echo "9. Testare endpoint protejat fără token (ar trebui să eșueze)..."
NO_TOKEN_RESPONSE=$(curl -s -X GET $BASE_URL/api/protected/test)

echo "Răspuns fără token: $NO_TOKEN_RESPONSE"
echo ""

echo "=== Testare Completă ==="
echo "Verifică răspunsurile de mai sus pentru a confirma că autentificarea funcționează corect."

