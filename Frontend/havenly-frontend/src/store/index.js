import { createStore } from 'vuex'

export default createStore({
  state: {
    darkMode: localStorage.getItem('darkMode') === 'true',

    idea: [
      {
        name: 'Shatha J',
        image: require('@/assets/img/1.jpg'),
        description: 'best website i have ever seen',
        rating: 3,
      },
      {
        name: 'John Cena',
        image: require('@/assets/img/2.jpg'),
        description: 'best website i have ever seen',
        rating: 2,
      },
      {
        name: 'Justin Bieber',
        image: require('@/assets/img/3.jpg'),
        description: 'best website i have ever seen',
        rating: 5,
      },
      {
        name: 'Super Man',
        image: require('@/assets/img/4.jpg'),
        description: 'best website i have ever seen',
        rating: 2,
      },
    ],


    tech: [
      {
        title: 'Flying Car releas',
        description: 'available on march',
        image: require('@/assets/img/1.jpg'),
        image2: require('@/assets/img/7.jpg'),
        image3: require('@/assets/img/7.jpg'),
        category: 'phone'
      },
      {
        title: 'Magic Holes',
        description: 'available on march',
        image: require('@/assets/img/2.jpg'),
        image2: require('@/assets/img/7.jpg'),
        image3: require('@/assets/img/7.jpg'),
        category: 'new tech'
      }
    ],

    phone: [
      // apple
      {
        title: 'Buildings',
        description: 'available on march',
        image: require('@/assets/img/2.jpg'),
        image2: require('@/assets/img/7.jpg'),
        image3: require('@/assets/img/7.jpg'),
        type: 'rent',
        location: 'Yangon',
        category: 'apple',
      },
      {
        title: 'J Donut',
        description: 'available on march',
        image: require('@/assets/img/2.jpg'),
        type: 'sell',
        location: 'Mandalay',
        category: 'apple',
      },
      {
        title: 'Cars',
        description: 'available on march',
        image: require('@/assets/img/2.jpg'),
        type: 'rent',
        location: 'Pago',
        category: 'apple',
      },
      {
        title: 'J Donut',
        description: 'available on march',
        image: require('@/assets/img/2.jpg'),
        type: 'sell',
        location: 'Mandalay',
        category: 'apple',
      },
      {
        title: 'Buildings',
        description: 'available on march',
        image: require('@/assets/img/2.jpg'),
        type: 'rent',
        location: 'Shan state',
        category: 'apple',
      },
      {
        title: 'Fishes',
        description: 'available on march',
        image: require('@/assets/img/2.jpg'),
        type: 'sell',
        location: 'Awadady',
        category: 'apple',
      },
      {
        title: 'Trees',
        description: 'available on march',
        image: require('@/assets/img/2.jpg'),
        type: 'rent',
        location: 'Kayin state',
        category: 'apple',
      },
      {
        title: 'For Show',
        description: 'available on march',
        image: require('@/assets/img/2.jpg'),
        type: 'sell',
        location: 'Madalay',
        category: 'apple',
      },



      {
        title: 'Phone Store',
        description: 'available on march',
        image: require('@/assets/img/3.jpg'),
        type: 'rent',
        location: 'Yangon',
        category: 'samsung',
      },
      {
        title: 'Burger',
        description: 'available on march',
        image: require('@/assets/img/3.jpg'),
        type: 'sell',
        location: 'KFC',
        category: 'samsung',
      },
      {
        title: 'Chicken Fried',
        description: 'available on march',
        image: require('@/assets/img/3.jpg'),
        type: 'rent',
        location: 'Mandalay',
        category: 'samsung',
      },
      {
        title: 'Coconut',
        description: 'available on march',
        image: require('@/assets/img/3.jpg'),
        type: 'sell',
        location: 'Yangon',
        category: 'samsung',
      },
      {
        title: 'Beach',
        description: 'available on march',
        image: require('@/assets/img/3.jpg'),
        type: 'rent',
        location: 'Ngwe Saung',
        category: 'samsung',
      },
      {
        title: 'Hotel',
        description: 'available on march',
        image: require('@/assets/img/3.jpg'),
        type: 'sell',
        location: 'Chaung Tha Beach',
        category: 'samsung',
      },
      {
        title: 'Animals',
        description: 'available on march',
        image: require('@/assets/img/3.jpg'),
        type: 'rent',
        location: 'Kyar State',
        category: 'samsung',
      },
      {
        title: 'Trees',
        description: 'available on march',
        image: require('@/assets/img/3.jpg'),
        type: 'sell',
        location: 'Kayin State',
        category: 'samsung',
      },



      {
        title: 'Trees',
        description: 'available on march',
        image: require('@/assets/img/5.jpg'),
        type: 'rent',
        location: 'AyerWady',
        category: 'xiaomi',
      },
      {
        title: 'J Donut',
        description: 'available on march',
        image: require('@/assets/img/5.jpg'),
        type: 'sell',
        location: 'Yangon',
        category: 'xiaomi',
      },
      {
        title: 'Weather',
        description: 'available on march',
        image: require('@/assets/img/5.jpg'),
        type: 'rent',
        location: 'Nay Pyi Taw',
        category: 'xiaomi',
      },
      {
        title: 'Fishes',
        description: 'available on march',
        image: require('@/assets/img/5.jpg'),
        type: 'sell',
        location: 'Ayarwady',
        category: 'xiaomi',
      },
      {
        title: 'Football',
        description: 'available on march',
        image: require('@/assets/img/5.jpg'),
        type: 'rent',
        location: 'Kayar state',
        category: 'xiaomi',
      },
      {
        title: 'Fishes',
        description: 'available on march',
        image: require('@/assets/img/5.jpg'),
        type: 'sell',
        location: 'Pago',
        category: 'xiaomi',
      },
      {
        title: 'Buildings',
        description: 'available on march',
        image: require('@/assets/img/5.jpg'),
        type: 'rent',
        location: 'Mandalay',
        category: 'xiaomi',
      },
      {
        title: 'Cows',
        description: 'available on march',
        image: require('@/assets/img/5.jpg'),
        type: 'sell',
        location: 'Yangon',
        category: 'xiaomi',
      },



      {
        title: 'Trees',
        description: 'available on march',
        image: require('@/assets/img/7.jpg'),
        type: 'rent',
        location: 'Pago',
        category: 'vivo',
      },
      {
        title: 'Clothes',
        description: 'available on march',
        image: require('@/assets/img/7.jpg'),
        type: 'sell',
        location: 'Shan state',
        category: 'vivo',
      },
      {
        title: 'Noodle',
        description: 'available on march',
        image: require('@/assets/img/7.jpg'),
        type: 'rent',
        location: 'Yangon',
        category: 'vivo',
      },
      {
        title: 'J Donut',
        description: 'available on march',
        image: require('@/assets/img/7.jpg'),
        type: 'sell',
        location: 'Mandalay',
        category: 'vivo',
      },
      {
        title: 'Book store',
        description: 'available on march',
        image: require('@/assets/img/7.jpg'),
        type: 'rent',
        location: 'Magway',
        category: 'vivo',
      },
      {
        title: 'Tom & Jerry',
        description: 'available on march',
        image: require('@/assets/img/7.jpg'),
        type: 'sell',
        location: 'Mon State',
        category: 'vivo',
      },
      {
        title: 'Kong Fu Panda',
        description: 'available on march',
        image: require('@/assets/img/7.jpg'),
        type: 'rent',
        location: 'Yangon',
        category: 'vivo',
      },
      {
        title: 'Hotel transylvania',
        description: 'available on march',
        image: require('@/assets/img/7.jpg'),
        type: 'sell',
        location: 'Nay pyi taw',
        category: 'vivo',
      },

    ],

    computer: [
      {
        title: 'computer 1',
        description: 'available on march',
        image: require('@/assets/img/7.jpg'),
        image2: require('@/assets/img/7.jpg'),
        image3: require('@/assets/img/7.jpg'),
      },
      {
        title: 'computer 2',
        description: 'available on march',
        image: require('@/assets/img/8.jpg'),
        image2: require('@/assets/img/7.jpg'),
        image3: require('@/assets/img/7.jpg'),
      }
    ],

  },
  getters: {
  },
  mutations: {
  },
  actions: {
  },
  modules: {
  }
})
