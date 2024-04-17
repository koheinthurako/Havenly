<template>
<div class="fourContact">
    <div class="fourContact-data">
        <div class="row g-0">
            <div class="col-6">
                <div class="card ms-auto">
                    <v-img :src="img1"></v-img>
                    <div class="card-body">
                      <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
                    </div>
                  </div>
            </div>
            <div class="col-6">
                <div class="card me-auto" >
                    
                    <div class="card-body">
                      <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
                    </div>
                    <v-img :src="img2"></v-img>
                  </div>
            </div>
            <div class="col-6">
                <div class="card ms-auto" >
                    <v-img :src="img3"></v-img>
                    <div class="card-body">
                      <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
                    </div>
                  </div>
            </div>
            <div class="col-6">
                <div class="card me-auto" >
                    
                    <div class="card-body">
                      <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
                    </div>
                    <v-img :src="img4"></v-img>
                  </div>
            </div>
        </div>
    </div>
</div>
</template>


<script>
export default {
    name: 'fourContectVue',

    data: ()=> ({
        img1: require('@/assets/img/6.jpg'),
        img2: require('@/assets/img/7.jpg'),
        img3: require('@/assets/img/8.jpg'),
        img4: require('@/assets/img/2.jpg'),

    }),
}

</script>

<style>
.fourContact {
    background-color: #F6F5F2;
    width: 100%;
    height: auto;
    display: flex;
    justify-content: center;
    align-items: center;
    overflow: hidden;
    padding: 60px 0px;

    .fourContact-data {
        width: 80%;
        height: auto;
        margin: auto;

    }
}

</style>