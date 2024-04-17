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

                <v-dialog v-model="dialog" max-width="550">
                    <v-stepper :items="['Package 1', 'Package 2', 'Package 3']">
                        <template v-slot:item.1>
                            <v-card title="Free Trial" flat>

                                <ol class="list-group list-group-numbered">
                                    <li class="p-2 rounded bg-danger"><div style="color: #fff;">Package Benefits</div></li>
                                    <li class="list-group-item">(2) posts available</li>
                                    <li class="list-group-item">(4) images per post</li>
                                    <li class="list-group-item">(800) words per post</li>
                                    <li class="list-group-item">Secure to upload posts</li>
                                    <li class="list-group-item"><del>post boost available</del></li>
                                    <li class="list-group-item"><del>showing less amount of ADs.</del></li>
                                    <li class="list-group-item"><del>can contact directly report to admin</del></li>
                                    <li class="list-group-item"><del>can send email to interested user</del></li>
                                </ol>

                            </v-card>
                        </template>

                        <template v-slot:item.2>
                            <v-card title="Normal package" flat>
                                <ol class="list-group list-group-numbered">
                                    <li class="p-2 rounded bg-danger"><div style="color: #fff;">Package Benefits</div></li>
                                    <li class="list-group-item">(12) posts available</li>
                                    <li class="list-group-item">(8) images per post</li>
                                    <li class="list-group-item">(1200) words per post</li>
                                    <li class="list-group-item">Secure to upload posts</li>
                                    <li class="list-group-item"><del>post boost available</del></li>
                                    <li class="list-group-item">showing less amount of ADs.</li>
                                    <li class="list-group-item">can contact directly report to admin</li>
                                    <li class="list-group-item">can send email to interested user</li>
                                </ol>
                            </v-card>
                        </template>

                        <template v-slot:item.3>
                            <v-card title="Premum Package" flat>
                                <ol class="list-group list-group-numbered">
                                    <li class="p-2 rounded bg-danger"><div style="color: #fff;">Package Benefits</div></li>
                                    <li class="list-group-item">(30) posts available</li>
                                    <li class="list-group-item">(12) images per post</li>
                                    <li class="list-group-item">(2000) words per post</li>
                                    <li class="list-group-item">Secure to upload posts</li>
                                    <li class="list-group-item">post boost available</li>
                                    <li class="list-group-item">Not showing annoying ADs.</li>
                                    <li class="list-group-item">can contact directly report to admin</li>
                                    <li class="list-group-item">can send email to interested user</li>
                                </ol>
                            </v-card>
                        </template>
                    </v-stepper>
                </v-dialog>

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
    methods: {
        openDialog() {
            this.dialog = true;
        },
        closeDialog() {
            this.dialog = false;
        }
    }
}
</script>