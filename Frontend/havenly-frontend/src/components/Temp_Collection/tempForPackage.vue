<template>
    <div class="temp-package">

        <div class="temp-package-data">
            <div class="temp-header-row ">
                <br>
                <div>
                    <p>Join us, {{ user.name }} !</p>
                    <h3 class="my-2 mx-auto">Choose a package according to your needs!</h3>
                </div>
                <div v-if="message !== ''">
                    {{message}}
                </div>
            </div>
            <div class="temp-package-row">

                <swiper ref="swiper" :keyboard="true" :loop="false" :effect="'coverflow'" :grabCursor="true"
                    :centeredSlides="true" :slidesPerView="'auto'" :navigation="true" :mousewheel="true"
                    :coverflowEffect="{
                        rotate: 10,
                        stretch: 10,
                        depth: 120,
                        modifier: 1.5,
                        slideShadows: true,
                    }" :modules="modules" @slideChange="handleSlideChange" class="mySwiper">
                    <swiper-slide>
                        <!-- package one start -->
                        <div class="temp-package">
                            <div v-if=package1>
                            <div style="line-height: 0px;">
                                <h3 class="d-flex">
                                    <div>{{package1.packName}}</div>&nbsp;&nbsp;
                                </h3>
                                <h5>For subscribers</h5>
                            </div>
                            <div>
                                <h1 class="mt-4 mb-5">$ {{package1.price}}</h1>
                            </div>

                            <div class="w-100 px-4">

                                <div class="d-flex"><v-icon class="icon-1">mdi-checkbox-marked-circle</v-icon>&nbsp;<p
                                        class="p1">Up to {{package1.total_posts}}
                                        posts a month.</p>
                                </div>
                                <div class="d-flex"><v-icon class="icon-2">mdi-checkbox-marked-circle</v-icon>&nbsp;<p
                                        class="p2">Up to {{package1.total_ads}}
                                        ads a month.
                                    </p>
                                </div>
                                <div class="d-flex"><v-icon class="icon-3">mdi-checkbox-marked-circle</v-icon>&nbsp;<p
                                        class="p3">3
                                        images per
                                        post.</p>
                                </div>
                                <div class="d-flex"><v-icon class="icon-4">mdi-checkbox-marked-circle</v-icon>&nbsp;<p
                                        class="p4">300+
                                        words per
                                        post.</p>
                                </div>
                            </div>
                            <div class="w-100 mt-5 d-flex justify-center temp-btn">
                                <v-btn elevation="20" class="sub-btn" to="/subscribe">
                                    <div class="toggle-v-l">
                                        <span class="visible">Try Free Trial</span>
                                        <span class="later-visible">Subscribe now</span>
                                    </div>
                                </v-btn>
                            </div>
                        </div>
                        <div v-else>No items found.</div>
                        </div>
                        <!-- package one end -->
                    </swiper-slide>
                    <swiper-slide>
                        <!-- package two start -->
                        <div class="temp-package">
                            <div v-if=package2>
                            <div style="line-height: 0px;">
                                <h3 class="d-flex">
                                    <div>{{package2.packName}}</div>&nbsp;&nbsp;package
                                </h3>
                                <h5>For average users</h5>
                            </div>
                            <div>
                                <h1 class="mt-4 mb-5">
                                    $ {{package2.price}}
                                </h1>
                            </div>

                            <div class="w-100 px-4">

                                <div class="d-flex"><v-icon class="icon-1">mdi-checkbox-marked-circle</v-icon>&nbsp;<p
                                        class="p1">Up to {{package2.total_posts}}
                                        posts a month.</p>
                                </div>
                                <div class="d-flex"><v-icon class="icon-2">mdi-checkbox-marked-circle</v-icon>&nbsp;<p
                                        class="p2">Up to {{package2.total_ads}}
                                        ads a month.
                                    </p>
                                </div>
                                <div class="d-flex"><v-icon class="icon-3">mdi-checkbox-marked-circle</v-icon>&nbsp;<p
                                        class="p3">Up to 3
                                        images per
                                        post.</p>
                                </div>
                                <div class="d-flex"><v-icon class="icon-4">mdi-checkbox-marked-circle</v-icon>&nbsp;<p
                                        class="p4">500+
                                        words per
                                        post.</p>
                                </div>

                            </div>
                            <div class="w-100 mt-5 d-flex justify-center temp-btn">
                                <v-btn elevation="20" class="sub-btn" @click="parseAndGoNext(package2)">
                                    <div class="toggle-v-l">
                                        <span class="visible">Buy Normal</span>
                                        <span class="later-visible">Buy now</span>
                                    </div>
                                </v-btn>
                            </div>
                        </div>
                        <div v-else>No items found.</div>
                        </div>
                        <!-- package two end -->
                    </swiper-slide>
                    <swiper-slide>
                        <!-- package three start -->
                        <div class="temp-package">
                            <div v-if=package3>
                            <div style="line-height: 0px;">
                                <h3 class="d-flex">
                                    <div>{{package3.packName}}</div>&nbsp;&nbsp;package
                                </h3>
                                <h5>For real-estate agents</h5>
                            </div>
                            <div>
                                <h1 class="mt-4 mb-5">
                                    $ {{package3.price}}
                                </h1>
                            </div>

                            <div class="w-100 px-4">

                                <div class="d-flex"><v-icon class="icon-1">mdi-checkbox-marked-circle</v-icon>&nbsp;
                                    <p
                                        class="p1">Up to {{package3.total_posts}}
                                        posts a month.</p>
                                </div>
                                <div class="d-flex"><v-icon class="icon-2">mdi-checkbox-marked-circle</v-icon>&nbsp;
                                    <p
                                        class="p2">Up to {{package3.total_ads}} ads a month.
                                    </p>
                                </div>
                                <div class="d-flex"><v-icon class="icon-3">mdi-checkbox-marked-circle</v-icon>&nbsp;<p
                                        class="p3">Up to 5
                                        images per
                                        post.</p>
                                </div>
                                <div class="d-flex"><v-icon class="icon-4">mdi-checkbox-marked-circle</v-icon>&nbsp;<p
                                        class="p4">1000+
                                        words per
                                        post.</p>
                                </div>

                            </div>
                            <div class="w-100 mt-5 d-flex justify-center temp-btn">
                                <v-btn elevation="20" class="sub-btn" @click="parseAndGoNext(package3)">
                                    <div class="toggle-v-l">
                                        <span class="visible">Buy Premium</span>
                                        <span class="later-visible">Buy now</span>
                                    </div>
                                </v-btn>
                            </div>
                            </div>
                            <div v-else>No items found.</div>
                        </div>
                        <!-- package three end -->
                    </swiper-slide>

                </swiper>

            </div>
        </div>
        <br>
        <br>
    </div>

</template>


<script>
import { Swiper, SwiperSlide } from 'swiper/vue';
import 'swiper/css';
// import Swal from 'sweetalert2';

import 'swiper/css/effect-coverflow';
import 'swiper/css/pagination';

import { EffectCoverflow, Pagination, Mousewheel, Keyboard } from 'swiper/modules';

import axios from 'axios';
import router from '@/router';

export default {
    name: 'tempForPackage',

    components: {
        Swiper,
        SwiperSlide,
    },
    setup() {

        return {
            modules: [EffectCoverflow, Pagination, Mousewheel, Keyboard],
        };
    },
    data(){
        return {

        availPosts:'',
        availAds:'',
        purchasedPackage:'',

        items: [],

        user: {
          name:'John Doe'
        },
        message: ''
      }
    },
    created(){
    const loginUserData = JSON.parse(sessionStorage.getItem('login_user'));
    if (loginUserData !==null ) { 
        if(loginUserData.nrc == null){ 
            this.message = "You must be subscribed to buy a package!";
            console.log("User is not subscribed");
        }else{
            this.purchasedPackage = loginUserData.packageName;
        }
        this.user.name = loginUserData.name;
       
    }
    else{
        console.log("User is not logged in.");   
        // router.push("/");
    }
    },
    mounted() {
      this.fetchData();
      this.fetchSubUserInfo();
    },
    computed: {
    package1() {
      return this.items.find(item => item.packageTypeId === 1);
    },
    package2() {
      return this.items.find(item => item.packageTypeId === 2);
    },
    package3() {
      return this.items.find(item => item.packageTypeId === 3);
    }
  },
    methods: {
        fetchData() {
        axios.get('http://localhost:8083/package-types/getall') 
          .then(response => {
            this.items = response.data;
            console.log("Package Types", this.items); 
          })
          .catch(error => {
            console.error('Error fetching data:', error);
          });
    },
        startCountAnimation(price) {
            this.hovering = true;
            this.animatedPrice = 0;
            clearInterval(this.interval);
            this.interval = setInterval(() => {
                this.animatedPrice += Math.ceil(price / 60);
                if (this.animatedPrice >= price) {
                    this.animatedPrice = price;
                    clearInterval(this.interval);
                }
            }, 20);
        },
        parseAndGoNext(item) {
            if(this.availPosts>0 && this.availAds>0 && this.purchasedPackage!=="Free Trial"){
                alert("You have packages left!");
            }else{
                // Parse data and store in session storage
      sessionStorage.setItem('packageData', JSON.stringify(item));       
      // Go to the next page
      router.push('/packages/payment'); 
            }
      
    },
    fetchSubUserInfo() {
                const user = JSON.parse(sessionStorage.getItem('login_user'));
                if(user.nrc!==null){
                  const nrc = user.nrc;
                  console.log("nrc to send backend to show subUser informations : " + nrc)
                  axios.get('http://localhost:8083/subscribe/getSubUser', {
                      params: {
                          nrc: nrc
                      }
                  })
                  .then(response => {
                    console.log(response.data);
                    this.availPosts = response.data.availPosts;
                    this.availAds = response.data.availAds;
                  })
                  .catch(error => {
                    console.error('Error fetching data:', error); // Handle the error
                  });  
                }else{
                    console.log("User is not subscribed.");

                }
                  
            },

    },

    // setup() {

    //     return {
    //         modules: [EffectCoverflow, Pagination, Mousewheel, Keyboard],

    //     };

    // },


    // computed: {
    //     user_info() {
    //         return this.$store.getters.Take_Userinfo
    //     }
    // },

}

</script>
