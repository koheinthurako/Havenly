<template>
  <div class="fifth-doc">
    <div class="container">
      <div class="fifth-doc-data">
        <div class="row p-0">
          <div class="col-md-12 p-2">


            <div class="top-doc color-white">
              <div class="image-overlay">
                <div class="overlay-text">
                  <h2>Some Documentations you can read</h2>
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
          <div class="col-md-4 p-2">

            <div class="button-box color-red">
              <div class="logo">
                <v-icon>mdi-text-box-multiple</v-icon>
              </div>
              <p><span class="ms-5"></span>Lorem ipsum dolor sit amet consectetur adipisicing elit. Dignissimos magnam
                obcaecati facere, eius accusantium iusto, laudantium, sint nostrum molestias recusandae earum quisquam
                pariatur cumque dolor? Iure magni exercitationem fuga quisquam!</p>
              <router-link class="custom-link" to="/">Read more <v-icon
                  class="custom-icon">mdi-arrow-right-bold</v-icon></router-link>
            </div>

          </div>

          <div class="col-md-4 p-2">

            <div class="button-box color-white">
              <div class="logo">
                <v-icon>mdi-star-box-multiple</v-icon>
              </div>
              <p><span class="ms-5"></span>Lorem ipsum dolor sit amet consectetur adipisicing elit. Dignissimos magnam
                obcaecati facere, eius accusantium iusto, laudantium, sint nostrum molestias recusandae earum quisquam
                pariatur cumque dolor? Iure magni exercitationem fuga quisquam!</p>
              <router-link class="custom-link" to="/">Read more <v-icon
                  class="custom-icon">mdi-arrow-right-bold</v-icon></router-link>
            </div>

          </div>
          <div class="col-md-4 p-2">

            <div class="button-box color-white">
              <div class="logo">
                <v-icon>mdi-play-box-multiple</v-icon>
              </div>
              <p><span class="ms-5"></span>Lorem ipsum dolor sit amet consectetur adipisicing elit. Dignissimos magnam
                obcaecati facere, eius accusantium iusto, laudantium, sint nostrum molestias recusandae earum quisquam
                pariatur cumque dolor? Iure magni exercitationem fuga quisquam!</p>
              <router-link class="custom-link" to="/">Read more <v-icon
                  class="custom-icon">mdi-arrow-right-bold</v-icon></router-link>
            </div>

          </div>

        </div>
      </div>
    </div>
  </div>
</template>


<script>
export default {
  name: 'fourContectVue',

  data: () => ({
    img1: require('@/assets/img/6.jpg'),
    img2: require('@/assets/img/7.jpg'),
    img3: require('@/assets/img/8.jpg'),
    img4: require('@/assets/img/2.jpg'),

  }),
}

</script>
