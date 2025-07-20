# Utiliser Python 3.12 slim comme base
FROM python:3.12-slim

# Définir le répertoire de travail
WORKDIR /app

# Copier le fichier requirements.txt et installer les dépendances
COPY requirements.txt .
RUN pip install --no-cache-dir -r requirements.txt

# Copier le code source de l'application
COPY app.py .

# Exposer le port utilisé par Flask
EXPOSE 5000

# Lancer le serveur Flask
CMD ["python", "app.py"]
