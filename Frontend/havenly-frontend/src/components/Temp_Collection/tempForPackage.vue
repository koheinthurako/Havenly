<template>
    <div class="temp-package">

        <div class="row">
            <div class="col-md-12 col-12">
                <div class="up-content">

                    <div class="img-text mx-auto">
                        <div class="mt-3 text-center">
                            <span>Pricing</span>
                            <h1>Explore our website packages now.</h1>
                            <p>Based on your desire you can choose 3 different kind of packages. Enjoy! </p>
                        </div>
                    </div>
                </div>
            </div>

            <div class="col-md-4">
                <div class="card-box">
                    <div class="card-box-data left">
                        <h2 class="text-center">Free trial</h2>
                        <v-card class="mx-auto">
                            <v-list :items="items"></v-list>
                        </v-card>
                        <div class="mt-2 w-100 d-flex justify-space-between">

                            <v-btn class="reg-btn">Register</v-btn>
                        </div>
                    </div>
                </div>

            </div>

            <div class="col-md-4">

                <div class="card-box">
                    <div class="card-box-data mid">
                        <h2 class="text-center">Premium Package</h2>
                        <h4 class="text-center">$800</h4>
                        <v-card class="mx-auto">
                            <v-list :items="items"></v-list>
                            <v-list :items="item2"></v-list>
                        </v-card>
                        <div class="mt-2 w-100 d-flex justify-space-between">
                            <v-btn class="sub-btn">Subscribe</v-btn>
                            <v-btn class="reg-btn">Register</v-btn>
                        </div>
                    </div>
                </div>

            </div>
            <div class="col-md-4">

                <div class="card-box">
                    <div class="card-box-data right">
                        <h2 class="text-center">Normal Package</h2>
                        <h4 class="text-center">$400</h4>
                        <v-card class="mx-auto">
                            <v-list :items="items"></v-list>
                        </v-card>
                        <div class="mt-2 w-100 d-flex justify-space-between">
                            <v-btn class="sub-btn">Subscribe</v-btn>
                            <v-btn class="reg-btn">Register</v-btn>
                        </div>
                    </div>
                </div>

            </div>

            <div class="col-md-12 col-12">
                <div class="pay-check">
                    <div class="pay-data">
                        <p><v-icon>mdi-lock</v-icon> Secured Payment by <span>2Checkout</span> with:</p>
                        <div class="ms-auto d-flex justify-center p-0 my-3">
                            <v-icon>mdi-cash</v-icon>
                            <v-icon>mdi-credit-card-outline</v-icon>
                            <v-icon>mdi-wallet</v-icon>
                        </div>
                        <p><span>info</span>: note something you would like to share to user.</p>
                    </div>
                </div>
            </div>
        </div>

    </div>
    <div class="sponsor-ship">
        <div class="row d-flex justify-center">
            <div class="col-md-2 col-sm-6">
                <v-img :src="img1"></v-img>
            </div>
            <div class="col-md-2 col-sm-6">
                <v-img :src="img2"></v-img>
            </div>
            <div class="col-md-2 col-sm-6">
                <v-img :src="img3"></v-img>
            </div>
            <div class="col-md-2 col-sm-6 ">
                <v-img :src="img4"></v-img>
            </div>
            <div class="col-md-2 col-sm-12">
                <v-img :src="img5"></v-img>
            </div>
        </div>
    </div>
</template>


<script>
export default {
    name: 'tempForPackage',

    data: () => ({
        img1: require('@/assets/img/logo-apple.svg'),
        img2: require('@/assets/img/logo-facebook.svg'),
        img3: require('@/assets/img/logo-nasa.svg'),
        img4: require('@/assets/img/logo-vodafone.svg'),
        img5: require('@/assets/img/logo-digitalocean.svg'),
        items: [
            { type: 'subheader', title: 'Basic Content' },
            {
                title: 'Item #1',
                value: 1,
            },
            {
                title: 'Item #2',
                value: 2,
            },
            {
                title: 'Item #3',
                value: 3,
            },
            { type: 'divider' },
            { type: 'subheader', title: 'Verification & Security' },
            {
                title: 'Item #4',
                value: 4,
            },
            {
                title: 'Item #5',
                value: 5,
            },
            {
                title: 'Item #6',
                value: 6,
            },

        ],
        item2: [
            { type: 'subheader', title: 'Customization' },
            {
                title: 'Item #8',
                value: 1,
            },
            {
                title: 'Item #9',
                value: 2,
            },
        ],
    }),
}

</script>