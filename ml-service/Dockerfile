# Utiliser Python 3.12 slim comme base
FROM python:3.12-slim

# Définir le répertoire de travail
WORKDIR /app

# Installer les dépendances système nécessaires à numpy, scipy, scikit-learn
RUN apt-get update && apt-get install -y \
    build-essential \
    gcc \
    gfortran \
    libatlas-base-dev \
    python3-dev \
    && rm -rf /var/lib/apt/lists/*

# Copier les fichiers
COPY requirements.txt .

# Augmenter les timeouts pour éviter les erreurs réseau
RUN pip install --upgrade pip && \
    pip install --default-timeout=120 --no-cache-dir -r requirements.txt

COPY app.py .

# Exposer le port utilisé par Flask
EXPOSE 5000

# Lancer le serveur Flask
CMD ["python", "app.py"]



