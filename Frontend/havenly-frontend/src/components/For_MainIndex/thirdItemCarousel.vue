  <template>
    <div class="third-item-carousel">
        <div class="container">
            <h2>Explore homes on Havenly.</h2>
        </div>
        <v-sheet class="mx-auto" elevation="6">
            <v-slide-group v-model="model" selected-class="bg-success" show-arrows>
                <v-slide-group-item v-for="n in 15" :key="n" v-slot="{ isSelected, toggle }">
                <v-card @click="toggle" elevation="20" rounded :color="isSelected ? 'primary' : undefined"
                        :disabled="loading" :loading="loading" class="mx-3 mt-3">
                        <template v-slot:loader="{ isActive }">
                            <v-progress-linear :active="isActive" color="deep-purple" height="4"
                                indeterminate></v-progress-linear>
                        </template>

                        <div class="row">
                            <div class="col-6">
                                <v-img :src="c1" height="100%"
                                    cover></v-img>
                            </div>
                            <div class="col-6">
                                <v-card-item>
                                    <v-card-title>Lat Phrao Condo</v-card-title>

                                    <v-card-subtitle>
                                        <span class="me-1">Local Favorite</span>

                                        <v-icon color="error" icon="mdi-fire-circle" size="small"></v-icon>
                                    </v-card-subtitle>
                                </v-card-item>

                                <v-card-text>
                                    <v-row align="center" class="mx-0">
                                        <v-rating :model-value="4.5" color="amber" density="compact" size="small"
                                            half-increments readonly></v-rating>

                                        <div class="text-grey ms-4">
                                            4.5 (413)
                                        </div>
                                    </v-row>
                                </v-card-text>
                                    <div class="my-4 text-subtitle-1">
                                        Bangkok Imue Noy
                                    </div>

              <br>

              <v-btn elevation="20" color="#E97559" style="border-radius: 30px;">Check Details</v-btn>

            </div>
            <div class="split right-part">
              hello world
            </div>

                                <v-card-actions>
                                    <v-btn color="deep-purple-lighten-2" text="Reserve" block border
                                        @click="reserve"></v-btn>
                                </v-card-actions>
                            </div>
                       
                    </v-card>
                </v-slide-group-item>
            </v-slide-group>
        </v-sheet>
    </div>
</template>



<script>
export default {
    // Your script goes here

    data: ()=> ({
        c1: require('@/assets/img/c7.jpg'),
    }),

}
</script>