// import { draftMode } from "next/headers";
import { postsQuery } from "../../../sanity/lib/queries";
import { cachedClient } from "../../../sanity/lib/client";
import BlogList from "@/components/BlogList";
import post from "../../../sanity/schemas/post";
// import { client } from "../../../sanity/lib/client";

export const revalidate = 30

export default async function Home() {
  //For Preview Mode
  // if (draftMode().isEnabled) {
  //   return <div>Preview Mode</div>;
  // }

  // const posts = await client.fetch(postsQuery);
  const posts = await cachedClient(postsQuery);

  return (
    <main>
      <BlogList posts={posts}/>
    </main>
  );
}
