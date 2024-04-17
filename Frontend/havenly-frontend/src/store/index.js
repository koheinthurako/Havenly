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
        title: 'Iphone 14 pro max',
        description: 'available on march',
        image: require('@/assets/img/2.jpg'),
        image2: require('@/assets/img/7.jpg'),
        image3: require('@/assets/img/7.jpg'),
        category: 'apple',
      },
      {
        title: 'Iphone 15 pro max',
        description: 'available on march',
        image: require('@/assets/img/2.jpg'),
        category: 'apple',
      },
      {
        title: 'Iphone 13 pro max',
        description: 'available on march',
        image: require('@/assets/img/2.jpg'),
        category: 'apple',
      },
      {
        title: 'Iphone 12 pro max',
        description: 'available on march',
        image: require('@/assets/img/2.jpg'),
        category: 'apple',
      },
      {
        title: 'Iphone 14 pro max',
        description: 'available on march',
        image: require('@/assets/img/2.jpg'),
        category: 'apple',
      },
      {
        title: 'Iphone 15 pro max',
        description: 'available on march',
        image: require('@/assets/img/2.jpg'),
        category: 'apple',
      },
      {
        title: 'Iphone 13 pro max',
        description: 'available on march',
        image: require('@/assets/img/2.jpg'),
        category: 'apple',
      },
      {
        title: 'Iphone 12 pro max',
        description: 'available on march',
        image: require('@/assets/img/2.jpg'),
        category: 'apple',
      },
      // Samsung
      {
        title: 'S24 ultra',
        description: 'available on march',
        image: require('@/assets/img/3.jpg'),
        category: 'samsung',
      },
      {
        title: 'S23 ultra',
        description: 'available on march',
        image: require('@/assets/img/3.jpg'),
        category: 'samsung',
      },
      {
        title: 'S22 ultra',
        description: 'available on march',
        image: require('@/assets/img/3.jpg'),
        category: 'samsung',
      },
      {
        title: 'S21 ultra',
        description: 'available on march',
        image: require('@/assets/img/3.jpg'),
        category: 'samsung',
      },
      {
        title: 'S24 ultra',
        description: 'available on march',
        image: require('@/assets/img/3.jpg'),
        category: 'samsung',
      },
      {
        title: 'S23 ultra',
        description: 'available on march',
        image: require('@/assets/img/3.jpg'),
        category: 'samsung',
      },
      {
        title: 'S22 ultra',
        description: 'available on march',
        image: require('@/assets/img/3.jpg'),
        category: 'samsung',
      },
      {
        title: 'S21 ultra',
        description: 'available on march',
        image: require('@/assets/img/3.jpg'),
        category: 'samsung',
      },
      // Xiaomi
      {
        title: 'Mi 11 ultra',
        description: 'available on march',
        image: require('@/assets/img/5.jpg'),
        category: 'xiaomi',
      },
      {
        title: 'Mi 12 ultra',
        description: 'available on march',
        image: require('@/assets/img/5.jpg'),
        category: 'xiaomi',
      },
      {
        title: 'Mi 11 ultra',
        description: 'available on march',
        image: require('@/assets/img/5.jpg'),
        category: 'xiaomi',
      },
      {
        title: 'Mi 10 ultra',
        description: 'available on march',
        image: require('@/assets/img/5.jpg'),
        category: 'xiaomi',
      },
      {
        title: 'Mi 11 ultra',
        description: 'available on march',
        image: require('@/assets/img/5.jpg'),
        category: 'xiaomi',
      },
      {
        title: 'Mi 12 ultra',
        description: 'available on march',
        image: require('@/assets/img/5.jpg'),
        category: 'xiaomi',
      },
      {
        title: 'Mi 11 ultra',
        description: 'available on march',
        image: require('@/assets/img/5.jpg'),
        category: 'xiaomi',
      },
      {
        title: 'Mi 10 ultra',
        description: 'available on march',
        image: require('@/assets/img/5.jpg'),
        category: 'xiaomi',
      },
      // Vivo
      {
        title: 'vivo 11 ultra',
        description: 'available on march',
        image: require('@/assets/img/7.jpg'),
        category: 'vivo',
      },
      {
        title: 'vivo 12 ultra',
        description: 'available on march',
        image: require('@/assets/img/7.jpg'),
        category: 'vivo',
      },
      {
        title: 'vivo 11 ultra',
        description: 'available on march',
        image: require('@/assets/img/7.jpg'),
        category: 'vivo',
      },
      {
        title: 'vivo 10 ultra',
        description: 'available on march',
        image: require('@/assets/img/7.jpg'),
        category: 'vivo',
      },
      {
        title: 'vivo 11 ultra',
        description: 'available on march',
        image: require('@/assets/img/7.jpg'),
        category: 'vivo',
      },
      {
        title: 'vivo 12 ultra',
        description: 'available on march',
        image: require('@/assets/img/7.jpg'),
        category: 'vivo',
      },
      {
        title: 'vivo 11 ultra',
        description: 'available on march',
        image: require('@/assets/img/7.jpg'),
        category: 'vivo',
      },
      {
        title: 'vivo 10 ultra',
        description: 'available on march',
        image: require('@/assets/img/7.jpg'),
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
