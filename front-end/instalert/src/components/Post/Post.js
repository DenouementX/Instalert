import React, {useRef} from "react";
import "./Post.css";
import meat from "../../images/meatball_menu.png";
import sprites from "../../images/sprite.png";
import share from "../../images/share-35-35.png";

function Post({nickname, avatar, image, caption}) {

    const postRef = useRef();

    return (
        <article className="Post" ref={postRef}>
            <header>
                <div className="Post-user">
                    <div className="Post-user-avatar">
                        <img src={avatar}  alt="avatar"/>
                    </div>
                    <div className="Post-user-nickname">
                        <span>{nickname}</span>
                    </div>
                    <div className="Post-user-meatball">
                        <img className="Meatball-menu" src={meat} alt="menu"/>
                    </div>
                </div>
            </header>
            <div className="Post-image">
                <div className="Post-image-bg">
                    <img src={image} alt="Post"/>
                </div>
            </div>
            <div className="Post-interactions">
                <div className="Post-interactions-left">
                    <img src={sprites} alt="Like" style={{
                        objectPosition: "-650px -660px"}}/>
                    <img src={sprites} alt="Comment" style={{
                        objectPosition: "-515px -352px"}}/>
                    <img src={share} alt="Share" style={{
                        transform: "scale(.85)"}}/>
                </div>
                <div className="Post-interactions-center" />
                <div className="Post-interactions-right">
                    <div className="Post-interactions-button">
                        <img src={sprites} alt="Comment" style={{
                            objectPosition: "-300px -711px"}}/>
                    </div>
                </div>
            </div>
            <div className="Post-caption">
                <div>
                    <strong>{nickname}</strong> {caption}
                </div>
            </div>
        </article>
    )
}
export default Post;