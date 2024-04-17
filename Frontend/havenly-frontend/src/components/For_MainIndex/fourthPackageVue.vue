<template>
    <div class="fourth-package">
        <div class="fourth-package-data">
            <div class="fourth-package-header mb-3">
                <div class="bar"></div>
                <h3>Choose Your Benefit</h3>
            </div>

            <div class="row">

                <div class="card p-0 col-md-3 col-12">
                    <v-img :src="img1" class="card-img-top" alt="Hello world"></v-img>
                    <div class="card-body">
                        <h5 class="card-title">Free Trial</h5>
                        <p class="card-text">Some quick example text to build on the card title and make up the bulk of
                            the
                            card's content.</p>
                        <v-btn :elevation="20" color="red" @click="openDialog">Check Details</v-btn>
                    </div>
                </div>

                <div class="card p-0 col-md-3 col-12">
                    <v-img :src="img2" class="card-img-top" alt="Hello world"></v-img>
                    <div class="card-body">
                        <h5 class="card-title">Normal Package</h5>
                        <p class="card-text">Some quick example text to build on the card title and make up the bulk of
                            the
                            card's content.</p>
                        <v-btn :elevation="20" color="red" @click="openDialog">Check Details</v-btn>
                    </div>
                </div>

                <div class="card p-0 col-md-3 col-12">
                    <v-img :src="img3" class="card-img-top" alt="Hello world"></v-img>
                    <div class="card-body">
                        <h5 class="card-title">Premium Package</h5>
                        <p class="card-text">Some quick example text to build on the card title and make up the bulk of
                            the
                            card's content.</p>
                        <v-btn :elevation="20" color="red" @click="openDialog">Check Details</v-btn>
                    </div>
                </div>


            </div>
        </div>

    </div>
</template>

<script>
export default {
    name: 'fourthPackageVue',

    data: () => ({
        dialog: false,

        img1: require('@/assets/img/1.jpg'),
        img2: require('@/assets/img/2.jpg'),
        img3: require('@/assets/img/3.jpg'),
  
    }),

}
</script>