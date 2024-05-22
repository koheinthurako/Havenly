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

            <event />

          </div>

          <div class="col-md-6 p-2">
            <div style="padding: 10px 0px; border-radius: 10px; box-shadow: inset 0px 0px 5px rgba(0, 0, 0, 0.5);">
              <ads_small_page />
            </div>
          </div>


        </div>

      </div>
    </div>
  </div>
</template>


<script>
import ads_small_page from '../Temp_Collection/ads-Small.vue'
import event from '../Temp_Collection/eventVue.vue'

export default {
  name: 'fourContectVue',
  components: {
    ads_small_page,
    event,
  },

  data: () => ({
    img1: require('@/assets/img/6.jpg'),
    img2: require('@/assets/img/7.jpg'),
    img3: require('@/assets/img/8.jpg'),
    img4: require('@/assets/img/2.jpg'),

  }),
}

</script>
