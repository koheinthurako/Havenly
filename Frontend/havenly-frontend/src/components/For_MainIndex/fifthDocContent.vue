<template>
  <div class="fifth-doc">
    <div class="container">
      <div class="fifth-doc-data">
        <div class="row p-0">
          <div class="col-md-12 p-2">


            <div class="top-doc color-white">
              <div class="image-overlay">
                <div class="overlay-text">
                  <h2>Documentation About our website</h2>
                  <p>Lorem ipsum dolor sit, amet consectetur adipisicing elit. Iste voluptates maxime ad quos,
                    temporibus quibusdam nulla. Voluptatibus, similique quisquam? Veniam optio exercitationem quos totam
                    ipsum perferendis ex esse, hic iure!</p>
                  <router-link to="/" class="custom-link">Read more <v-icon
                      class="custom-icon">mdi-arrow-right-bold</v-icon></router-link>
                </div>
              </div>
            </div>



          </div>

        </div>

        <div class="row">
          <div class="col-md-6 p-2">

            <div class="button-box color-red">

              <h3 class="text-center my-2">Up coming events</h3>
              <router-link to="/" class="nav-link">
                <div class="d-flex"><v-icon>mdi-calendar-alert</v-icon>
                  <p>&nbsp;From 01 Apr To 30 Apr</p>
                </div>
                <div class="d-flex"><v-icon>mdi-map-marker-check</v-icon>&nbsp;<p>Lotte Hotel Yangon</p>
                </div>
              </router-link>
              <v-divider :thickness="2" class="border-opacity-50 mt-0"></v-divider>
              <router-link to="/" class="nav-link">
                <div class="d-flex"><v-icon>mdi-calendar-alert</v-icon>
                  <p>&nbsp;From 01 Apr To 30 Apr</p>
                </div>
                <div class="d-flex"><v-icon>mdi-map-marker-check</v-icon>&nbsp;<p>Bangkok Projects Special Sales Event
                  </p>
                </div>
              </router-link>
              <router-link class="custom-link" to="/">See all events <v-icon
                  class="custom-icon">mdi-arrow-right-bold</v-icon></router-link>
            </div>

          </div>

          <div class="col-md-6 p-2">
            <adspage />
          </div>


        </div>

      </div>
    </div>
  </div>
</template>


<script>
import adspage from '../Temp_Collection/ads-Small.vue'
export default {
  name: 'fourContectVue',
  components: {
    adspage,
  },

  data: () => ({
    img1: require('@/assets/img/6.jpg'),
    img2: require('@/assets/img/7.jpg'),
    img3: require('@/assets/img/8.jpg'),
    img4: require('@/assets/img/2.jpg'),

  }),
}

</script>
