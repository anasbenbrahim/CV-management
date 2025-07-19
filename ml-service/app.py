from flask import Flask, request, jsonify
from flask_cors import CORS
from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.metrics.pairwise import cosine_similarity

app = Flask(__name__)
CORS(app)  # Enable CORS for cross-origin requests

def calculate_similarity(cv_text, mission_text):
    if not cv_text or not mission_text:
        return 0.0
    vectorizer = TfidfVectorizer()
    tfidf_matrix = vectorizer.fit_transform([cv_text, mission_text])
    similarity = cosine_similarity(tfidf_matrix[0:1], tfidf_matrix[1:2])[0][0]
    return float(similarity)

@app.route('/predict')
def predict():
    cv_text = request.args.get('cv_text', '')
    mission_text = request.args.get('mission_text', '')
    print(f"CV Text: {cv_text}, Mission Text: {mission_text}")
    score = calculate_similarity(cv_text, mission_text)
    return jsonify({"score": score})  # ✅ JSON correct

if __name__ == '__main__':
    app.run(host='0.0.0.0', port=5000, debug=True)
