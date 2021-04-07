const product =
{
  id: 1,
  name: '만두 지갑',
  originalPrice: 50000,
  discountedPrice: 40000,
  description: '설명',
  images: [{
    id: 1,
    url: 'url1',
  }, {
    id: 2,
    url: 'url2',
  }],
  options: [
    {
      id: 1,
      name: '색상',
      additionalPrice: 0,
      children: [
        {
          id: 10,
          name: '갈색',
          additionalPrice: 1000,
          children: [],
        },
        {
          id: 10,
          name: '남색',
          additionalPrice: 2000,
          children: [],
        },
      ],
    },
  ],
};

export default product;
