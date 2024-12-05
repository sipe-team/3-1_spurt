import http from "k6/http";

export const options = {
    vus: 10,
    duration: '60s',
    noConnectionReuse: true,
}

export default async function() {
    const url = 'http://localhost:8080/products/n?cache=true';
    http.get(url);
}

// echo "GET http://localhost:8080/products/n?cache=true" | vegeta attack -duration=30s -rate=0 -max-workers=4 | vegeta report -type=text > second-level-cache.txt
// echo "GET http://localhost:8080/products/n?cache=false" | vegeta attack -duration=30s -rate=0 -max-workers=4 | vegeta report -type=text > non-second-level-cache.txt
