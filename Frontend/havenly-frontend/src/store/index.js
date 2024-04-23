import { createStore } from 'vuex'

export default createStore({
  state: {
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
      // Samsung
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
      // Xiaomi
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
      // Vivo
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

    // to Store Register Data 
    registerData: [],

    // Login Id as none ;)
    LoginedId: localStorage.getItem('LoginedId') || 'none',

    // Login Status
    Logined: localStorage.getItem('Logined') === 'false',

    // Registered start
    Registered: localStorage.getItem('Registered') === 'false',

  },
  getters: {

    get_LoginedId(state) {
      return state.LoginedId;
    },

    //Take data from page to confirm registered or Not
    darkMode(state) {
      return state.darkMode;
    },

    LoginData(state) {
      return state.Logined;
    },

    RegisterData(state) {
      return state.Registered
    },

    Take_Userinfo: (state) => {
      const user = state.registerData.find(user => user.id === state.LoginedId);
      return user ? user : null;
  }

  },
  mutations: {

    // Register Section start 
    Register_mut(state, getData) {
      let temp = 0;
      const isEmpty = state.registerData.length === 0;

      if (!isEmpty) {

        // If any data exist in registerData check email is Registered or not
        for (const pp of state.registerData) {
          for (const gg of getData) {
            if (pp.gmail == gg.usermail) {
              if (pp.name == gg.username) {
                temp = 1;
              }
            }
          }
        }

        // if Eamil is not Registered add as a new user
        if (temp == 0) {
          for (const gg of getData) {
            let id = state.registerData.length+1;
            state.registerData.push({
              id: id,
              name: gg.username,
              gmail: gg.usermail,
              pass: gg.password
            })
            state.LoginedId = id;
            localStorage.setItem('LoginedId', state.LoginedId);
          }

          state.Registered = !state.Registered;
          localStorage.setItem('Registered', state.Registered);

          state.Logined = !state.Logined;
          localStorage.setItem('Logined', state.Logined);

        }

      } else {

        // this section will do when no data exist in DB
        for (const gg of getData) {
          let id = state.registerData.length+1;
          state.registerData.push({
            id: id,
            name: gg.username,
            gmail: gg.usermail,
            pass: gg.password
          })
          state.LoginedId = id;
          console.log("all data : "+state.registerData.id);
          localStorage.setItem('LoginedId', state.LoginedId);
        }

        state.Registered = !state.Registered;
        localStorage.setItem('Registered', state.Registered);

        state.Logined = !state.Logined;
        localStorage.setItem('Logined', state.Logined);
      }
    },
    // Register Section end

    // Logout Mutation
    LogoutMutation(state) {
      console.log('Logout mutation');
      console.log("check logout or not "+state.Logined);
      console.log("check Loginedid "+state.LoginedId);
      state.Logined = !state.Logined;
      localStorage.setItem('Logined', state.Logined);

      state.LoginedId = 'none';
      localStorage.setItem('LoginedId', state.LoginedId);
    },

    LoginMutation(state, get_id) {
      state.Logined = !state.Logined;
      localStorage.setItem('Logined', state.Logined);

      state.LoginedId = get_id;
      localStorage.setItem('LoginedId', state.LoginedId);
    }

  },
  actions: {

    // Take Action from Pages
    Register_Action(context, getData) {
      context.commit('Register_mut', getData);
    },

    // Take Login Action From Page
    login_confirm(context) {
      context.commit('Login_c_mut');
    },

    // Logout Action
    To_Logout_Action(context) {
      console.log('Logout action in');
      context.commit('LogoutMutation');
    },

    // Login Action
    To_Login_Action(context, get_id) {
      context.commit('LoginMutation', get_id);
    }

  },
  modules: {
  }
})
