<template>
    <div>
        <div>
            <b-carousel
                    :interval="4000"
                    indicators
                    img-width="1240"
                    img-height="550">
                <b-carousel-slide style="max-height: 550px">
                    <img
                            slot="img"
                            class="d-block img-fluid w-100"
                            src="../assets/images.jpg"
                            alt="image slot">
                </b-carousel-slide>
                <b-carousel-slide style="max-height: 550px; background-color: #a4e271">
                    <img style="max-height: 600px"
                         slot="img"
                         class="d-block img-fluid w-100"
                         src="../assets/logo.png"
                         alt="image slot">
                </b-carousel-slide>
                <b-carousel-slide style="max-height: 550px">
                    <img
                            slot="img"
                            class="d-block img-fluid w-100"
                            src="../assets/iStock-862477938.jpg"
                            alt="image slot">
                </b-carousel-slide>
            </b-carousel>
        </div>

        <div class="container">
            <div class="best">
                <h1>Expiration Date</h1>
            </div>
            <div class="row mb-5">
                <div class="col-sm-4">
                    <img src="../assets/milk.png" alt="picture">
                </div>
                <div class="col-sm-4" style="align-content: center">
                    <img src="../assets/liha.png" alt="picture">
                </div>
                <div class="col-sm-4">
                    <img src="../assets/vorst.png" alt="picture">
                </div>
            </div>
        </div>
        <div class="jumbotron">
            <div class="container" style="text-align: center">
                <h4 style="font-family: Gotham">
                    Life expectancy would grow by leaps and bounds if green vegetables smelled as good as bacon -

                    Doug Larson
                </h4>
            </div>
        </div>
        <div class="container">
            <div class="row mb-5">
                <div class="col-md-4">
                    <img src="../assets/healthy.jpg" alt="picture">
                </div>
                <div class="col-md-4" style="align-content: center">
                    <img src="../assets/images.png" alt="picture">
                </div>
                <div class="col-md-4">
                    <img src="../assets/food_pyramid.jpg" alt="picture">
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    export default {
        name: "homepageBody"
    }
</script>

<style scoped>
    .container {
        display: flex;
        flex-direction: column;
        width: 80%;
        position: relative;
        margin: 6% auto auto;
    }

    .best {
        text-align: left;
    }

</style>