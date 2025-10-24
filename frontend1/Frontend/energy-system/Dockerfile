# === Etapa unică pentru development ===
FROM node:20-alpine

# Setează directorul de lucru
WORKDIR /app

# Copiază fișierele de configurare
COPY package*.json ./

# Instalează dependențele
RUN npm install

# Copiază restul codului sursă
COPY . .

# Expune portul folosit de Vite
EXPOSE 5173

# Rulează Vite dev server cu opțiunea --host (necesară pentru acces din container)
CMD ["npm", "run", "dev", "--", "--host"]
