export async function fetchProducts() {
  const url = 'http://localhost:8080/api/products';
  const response = await fetch(url);
  const data = await response.json();
  return data;
}

export async function fetchProduct(productId) {
  const url = `http://localhost:8080/api/products/${productId}`;
  const response = await fetch(url);
  const data = await response.json();
  return data;
}

export async function postProduct(product) {
  const url = 'http://localhost:8080/api/products';
  const response = await fetch(url, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(product),
  });

  return response;
}

export async function postSignUp({ email, name, password }) {
  const url = 'http://localhost:8080/api/users';
  const response = await fetch(url, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify({ email, name, password }),
  });

  return response;
}

export async function postLogin({ email, password }) {
  const url = 'http://localhost:8080/session';
  const response = await fetch(url, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify({ email, password }),
  });

  const { accessToken } = await response.json();
  return accessToken;
}

