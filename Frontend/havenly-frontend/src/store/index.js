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
      {
        title: 'Khlong Luxury Condo',
        description: 'Rama 4 Road, Maha Phrutharam, Bang Rak, Krabi 10 mins (820 m) from BL28 Hua Lamphong MRT',
        image: require('@/assets/img/c1.jpg'),
        category: 'apple',
      },
      {
        title: 'Bangkok DB Condo',
        description: '78 Soi Sukhumvit 61, Khlong Tan Nua, Watthana, Bangkok 20 mins (1.7 km) from E6 Thong Lo BTS',
        image: require('@/assets/img/c2.jpg'),
        category: 'apple',
      },
      {
        title: 'Peat Dinna Condo',
        description: 'Just 82,xxx, 2beds 2baths, newly renovate, high floor,near BTS Thonglo. For Sale The Clover Thonglor',
        image: require('@/assets/img/c3.jpg'),
        category: 'apple',
      },
      {
        title: 'Plai Phraya Condo',
        description: '157 15 Ratchawithi Rd., Thung Phaya Thai, Ratchathewi, Bangkok 5 min (380 m) from N1 Ratchathewi BTS',
        image: require('@/assets/img/c4.jpg'),
        category: 'apple',
      },
      {
        title: 'Phela Bangkok',
        description: 'Live the uniquely charming lifestyle of Sam Yan at Culture Chula. 38/12 Soi Pratu Chai, Si Phraya, Bang Rak, Bangkok',
        image: require('@/assets/img/c5.jpg'),
        category: 'apple',
      },
      {
        title: 'Lam Thap Luxury',
        description: '99 Ratchaphruek Road, Bukkalo, Thon Buri, Bangkok 4 mins (320 m) from S9 Pho Nimit BTS',
        image: require('@/assets/img/c6.jpg'),
        category: 'apple',
      },
      {
        title: 'Phasi Charoen',
        description: '2 Silom Road, Silom, Bang Rak, Bangkok 6 mins (500 m) from S3 Chong Nonsi BTS',
        image: require('@/assets/img/c7.jpg'),
        category: 'apple',
      },
      {
        title: 'Min buri Condo',
        description: 'Krung Thon Buri Road, Bang Lam Phu Lang, Khlong San, Bangkok 7 mins (580 m) from S8 Wongwian Yai BTS',
        image: require('@/assets/img/c8.jpg'),
        category: 'apple',
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
