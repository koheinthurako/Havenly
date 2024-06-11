<template>
    <dir class="about-in-main">
        <div class="container">
            <div class="row">
                <div class="col-md-3 col-12">
                    <div class="card-box">
                        <div class="overlay-circle"></div>
                        <div @click="goTo('createAcc')" class="inner-box">
                            <div class="top-circle">
                                <v-icon>mdi-account</v-icon>
                            </div>
                            <div class="header">
                                <h4>Create Account</h4>
                            </div>
                            <div class="content">

                                <p>If you want to create an account goto login inside the navbar. If you reached login
                                    page then click create account. see more ...</p>

                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-md-3 col-12">
                    <div class="card-box">
                        <div class="overlay-circle"></div>
                        <div @click="goTo" class="inner-box">
                            <div class="top-circle">
                                <v-icon>mdi-cart</v-icon>
                            </div>
                            <div class="header">
                                <h4>Buying Package</h4>
                            </div>
                            <div class="content">

                                <p>After creating an account, you can buy a package. There have multiple packages we
                                    offered with fair prices. see more ...</p>

                            </div>
                        </div>
                    </div>
                </div>

                <div class="col-md-3 col-12">
                    <div class="card-box">
                        <div class="overlay-circle"></div>
                        <div @click="goTo" class="inner-box">
                            <div class="top-circle">
                                <v-icon>mdi-upload</v-icon>
                            </div>
                            <div class="header">
                                <h4>Uploading posts</h4>
                            </div>
                            <div class="content">

                                <p>After subscribe a package successfully, you can sell or rent your properties by
                                    creating posts. Click to see more ...</p>

                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-md-3 col-12">
                    <div class="card-box">
                        <div class="overlay-circle"></div>
                        <div @click="goTo" class="inner-box">
                            <div class="top-circle">
                                <v-icon>mdi-bookmark-outline</v-icon>
                            </div>
                            <div class="header">
                                <h4>Making interest</h4>
                            </div>
                            <div class="content">

                                <p>If you interested a post you can request owner to get more info of post or you can
                                    contact directly to make discussion. see more ...</p>

                            </div>
                        </div>
                    </div>
                </div>

            </div>

        </div>
    </dir>
</template>

<script>
export default {
    name: 'AboutInMain',

    date: () => ({

    }),

    methods: {
        truncateText(text, charLimit) {
            if (text.length > charLimit) {
                return text.slice(0, charLimit) + '...';
            }
            return text;
        },

        goTo() {
            // this.$router.push({ name: 'About' });
            this.$router.push({ name: 'About' });
        }
    }
}

</script>