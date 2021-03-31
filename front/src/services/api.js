export default async function fetchProducts() {
  const url = 'http://localhost:8080/api/products';
  const response = await fetch(url);
  const data = await response.json();
  return data;
}
